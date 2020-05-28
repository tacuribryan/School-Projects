#include "SharedMemoryIO.h"
#include "MailBoxMessage.h"
#include "ConnectionList.h"
#include "LinkedList.h"
#include <windows.h>
#include <stdio.h>
#include <conio.h>
#include <tchar.h>

int BUF_SIZE = 256;
#define NO_DATA 999

ConnectionList* pConnectionList = NULL;
MsgList* pMsgList = NULL;
HANDLE         serverHandle = NULL;

HANDLE pThread = NULL;
DWORD thread_id = 0;
BOOL  running_flag = TRUE;

MailBoxMessage* pTempReadBuffer;

int            MaxMessageSize = 0;

SharedMemoryIO::SharedMemoryIO()
{
	//This maintains a lookup table to the memory buffer for every connection
	pConnectionList = new ConnectionList();
	pMsgList = new MsgList();
	pTempReadBuffer = new MailBoxMessage();
}

SharedMemoryIO::~SharedMemoryIO()
{
	running_flag = FALSE;
	//unmap all views
	BOOL flag = TRUE;
	LPCTSTR pBuf = pConnectionList->PeekTail();
	while (flag && pBuf != NULL) {
		UnmapViewOfFile(pBuf);
		flag = pConnectionList->RemoveTail();
		pBuf = pConnectionList->PeekTail();
	}

	if (pThread != NULL)
		CloseHandle(pThread);

	delete pMsgList;
	delete pConnectionList;
}

//To open a connection to an existing mailbox, you need to call two function:
//1) call "OpenFileMapping" with the name of the mail box you need to access.It returns an address to the shared memory
//2) call "MapViewOfFile" to get a pointer to the shared memory. This is done for you in "GetBuffer"
HANDLE SharedMemoryIO::myCreateFile(LPCSTR lpFileName, DWORD dwDesiredAccess, DWORD dwShareMode, LPSECURITY_ATTRIBUTES lpSecurityAttributes, DWORD dwCreationDisposition, DWORD dwFlagsAndAttributes, HANDLE hTemplateFile)
{
#ifdef MICROSOFT_IMPLEMENTATION
	return CreateFileA(lpFileName, dwDesiredAccess, dwShareMode, lpSecurityAttributes, dwCreationDisposition, dwFlagsAndAttributes, hTemplateFile);
#else
	HANDLE	    hMapFile = NULL;
	LPCTSTR     pBuf = NULL;
	LPTSTR      mailbox_id = LPCSTRtoLPTSTR(lpFileName);

	//TODO 1): request access to an already created share memory, by calling OpenFileMapping
		//use FILE_MAP_ALL_ACCESS to specify the access
		//do not inherit the name
		//use mailbox_id for the name
		//save the return address in variable declared above: hMapFile 

		//call OpenFileMapping here

	hMapFile = OpenFileMapping(
		FILE_MAP_ALL_ACCESS,
		FALSE,
		mailbox_id);

	//END of TODO

	if (hMapFile != NULL)
	{
		//2) get a pointer to the address
		pBuf = GetBuffer(hMapFile);
		if (pBuf != NULL)
			pConnectionList->Add(hMapFile, pBuf);
	}

	return hMapFile;
#endif
}

//This function returns the number of messages waiting in the queue
BOOL SharedMemoryIO::myGetMailslotInfo(HANDLE  hMailslot, LPDWORD lpMaxMessageSize, LPDWORD lpNextSize, LPDWORD lpMessageCount, LPDWORD lpReadTimeout)
{
#ifdef MICROSOFT_IMPLEMENTATION
	return GetMailslotInfo(hMailslot, lpMaxMessageSize, lpNextSize, lpMessageCount, lpReadTimeout);
#else
	* lpMessageCount = pMsgList->GetCount();
	return (lpMessageCount > 0);
#endif
}

//Each process own one mailbox which it has to create
//To create the share memory for the mailbox:
//1) call "CreateFileMapping" with a unique name. It returns a handle to the share memory
//2) Use the return handle to call "MapViewOfFile" to get a pointer to the shared memory.
// "MapViewOfFile" is called in "GetBuffer"
HANDLE SharedMemoryIO::myCreateMailslot(LPCSTR lpName, DWORD nMaxMessageSize, DWORD lReadTimeout, LPSECURITY_ATTRIBUTES lpSecurityAttributes)
{
#ifdef MICROSOFT_IMPLEMENTATION
	HANDLE hSlot = CreateMailslotA(lpName, nMaxMessageSize, lReadTimeout, lpSecurityAttributes);
	return hSlot;
#else
	LPTSTR unique_id = LPCSTRtoLPTSTR(lpName);

	//TODO: uncomment the next statement and fill in where you ?

serverHandle = CreateFileMapping(
	INVALID_HANDLE_VALUE,			  // use paging file 
	lpSecurityAttributes,			 //security
	PAGE_READWRITE,					// read/write access                 
	0,							   // maximum object size (high-order DWORD)                 
	nMaxMessageSize,              // maximum object size (low-order DWORD)                 
	unique_id);                  // name of mapping object

	//END of TODO

	if (serverHandle != NULL)
	{
		serverBuf = GetBuffer(serverHandle);
		if (serverBuf != NULL)
		{
			MaxMessageSize = nMaxMessageSize;
			EmptyMailbox(serverBuf);
			pConnectionList->Add(serverHandle, serverBuf);
			pThread = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)thread_func, (LPVOID)this, 0, &thread_id);
		}
	}
	return serverHandle;
#endif
}

LPCSTR SharedMemoryIO::LPTSTRtoLPCSTR(LPTSTR data)
{
	CHAR* tmp1 = new CHAR[64];
	::WideCharToMultiByte(CP_ACP, 0, data, -1, tmp1, 64, NULL, FALSE);
	return tmp1;
}

LPTSTR SharedMemoryIO::LPCSTRtoLPTSTR(LPCSTR data)
{
	TCHAR* tmp1 = new TCHAR[64];
	::MultiByteToWideChar(CP_ACP, 0, data, -1, tmp1, 64);
	return tmp1;
}

//This function return a pointer to the shared memory address, by calling mapviewofile
LPCTSTR SharedMemoryIO::GetBuffer(HANDLE hMapFile)
{
	LPCTSTR pBuf = (LPTSTR)MapViewOfFile(hMapFile,	        // handle to map object
		FILE_MAP_ALL_ACCESS,	// read/write permission   
		0, 0, BUF_SIZE);
	return pBuf;
}

BOOL SharedMemoryIO::myWriteFile(HANDLE hFile, LPCVOID lpBuffer, DWORD nNumberOfBytesToWrite, LPDWORD lpNumberOfBytesWritten, LPOVERLAPPED lpOverlapped)
{
#ifdef MICROSOFT_IMPLEMENTATION
	//return WriteFile(hFile, lpBuffer, nNumberOfBytesToWrite, lpNumberOfBytesWritten, lpOverlapped);
#else
	//TODO: Comment out the line above. Your implementation goes here.
	//To send a message to another mailbox, you simply write the data to its shared memory location
	//The address of the shared memory is maintained by "pConnectionList"
	//So use "pConnectionList" to get a pointer to that address, it the memory address is not NULL
	//Then call the function "Write" and return TRUE

	PVOID var = (PVOID)pConnectionList->PeekBuffer(hFile);
	Write(var, (PVOID)lpBuffer, nNumberOfBytesToWrite);
	return true;
#endif
}

//Each mailbox owner can read incoming messages by just reading its own shared memory, when a message is found
//it is placed on a message queue. see the function MonitorIncomingData
//This function copies the message on the top of the message queue "pMsgList" to "lpBuffer" and return TRUE
//If the nessage queue is empty, it returns FALSE
BOOL SharedMemoryIO::myReadFile(HANDLE hFile, LPVOID lpBuffer, DWORD nNumberOfBytesToRead, LPDWORD lpNumberOfBytesRead, LPOVERLAPPED lpOverlapped)
{
#ifdef MICROSOFT_IMPLEMENTATION
	return ReadFile(hFile, lpBuffer, nNumberOfBytesToRead, lpNumberOfBytesRead, lpOverlapped);
#else
	if (pMsgList->GetCount() > 0)
	{
		BOOL return_val = pMsgList->Get((MailBoxFormat*)lpBuffer);
		return TRUE;
	}
	return FALSE;
#endif
}

//This function send message by performing a memory copy using "CopyMemory"
void SharedMemoryIO::Write(PVOID dest, PVOID source, int size)
{
	CopyMemory(dest, source, size);
}

//This function reads message by performing a memory copy using "CopyMemory"
void SharedMemoryIO::Read(PVOID dest, PVOID source, int size)
{
	CopyMemory(dest, source, size);
}

void SharedMemoryIO::EmptyMailbox(LPCTSTR pBuf)
{
	int data = NO_DATA;

	if (pBuf != NULL)
		Write((LPTSTR)pBuf, (LPTSTR)&data, sizeof(int));
}

void SharedMemoryIO::MonitorIncomingData(LPCTSTR shared_memory_to_monitor)
{
	int data;
	LPTSTR local_serverBuf = (LPTSTR)shared_memory_to_monitor;
	if (shared_memory_to_monitor != NULL)
	{
		Read(&data, local_serverBuf, sizeof(int));
		if (data != NO_DATA)
		{
			Read((PVOID)pTempReadBuffer->data, local_serverBuf, MaxMessageSize);
			if (pTempReadBuffer->IsValidData())
			{
				pMsgList->Put(pTempReadBuffer->data);
				EmptyMailbox(local_serverBuf);
			}
		}
	}
}

//This funtion is the main server thread that monitors incoming tweets. 
//You have to determine where in the code above it is best to start this thread by calling:
//pThread = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)thread_func, (LPVOID)this, 0, &thread_id);
DWORD WINAPI SharedMemoryIO::thread_func(LPVOID lpParam) {
	while (running_flag)
	{
		((SharedMemoryIO*)lpParam)->MonitorIncomingData(((SharedMemoryIO*)lpParam)->serverBuf);
		Sleep(1);
	}
	return 0;
}