#include<iostream>;
#include<iomanip>;
#include<string>;
#include<fstream>;

using namespace std;
void getData(int aid[], float aheight[], float aweight[], int aage[]);
void processHatSize(float bheight[], float bweight[], int bage[], int bsize[]);
void processJacketSize(float bheight[], float bweight[], int bage[], int bsize[]);
void processPantSize(float bheight[], float bweight[], int bage[], int bsize[]);
void printResults(int id[], float height[], float weight[], int hatSize[], int jacketSize[], int pantSize[]);


int  main() {

	int id[10], age[10], hatSize[10], jacketSize[10],pantSize[10];
	float height[10], weight[10];

	getData(id, height, weight, age);
	processHatSize(height, weight, age, hatSize);
	processJacketSize(height, weight, age, jacketSize);
	processPantSize(height, weight, age, pantSize);
	printResults(id, height, weight, hatSize, jacketSize, pantSize);


	return 0;

}

void getData(int aid[], float aheight[], float aweight[], int aage[])
{
	int i = 0;
	ifstream inData;
	inData.open("CustomerFile.txt");

	for (i = 0; i<10; i++)
	{
		inData >> aid[i] >> aheight[i] >> aweight[i] >> aage[i];
	}
	inData.close();
	return;
}

void processHatSize(float bheight[], float bweight[], int bage[], int bsize[])
{
	for (int i = 0; i<10; i++)
	{
		bsize[i] = (bweight[i] / bheight[i])* 2.9;
	}
	return;
}
void processJacketSize(float bheight[], float bweight[], int bage[], int bsize[])
{
	for (int i = 0; i<10; i++)
	{
			bsize[i] = bheight[i] * bweight[i] /288;
		if (bage[i] >= 40) {
			bsize[i] += (1 / 8);
		}
		if (bage[i] >= 50) {
			bsize[i] += (1 / 8);
		}
		if (bage[i] >= 60) {
			bsize[i] += (1 / 8);
		}
		if (bage[i] >= 70) {
			bsize[i] += (1 / 8);
		}
		if (bage[i] >= 80) {
			bsize[i] += (1 / 8);
		}
	}
	return;
}
void processPantSize(float bheight[], float bweight[], int bage[], int bsize[])
{
	for (int i = 0; i<10; i++)
	{
		bsize[i] = bweight[i] / 5.7;
	}
	return;
}
void printResults(int id[], float height[], float weight[], int hatSize[], int jacketSize[], int pantSize[])
{
	ofstream outData;
	outData << setiosflags(ios::fixed | ios::showpoint | ios::right);
	outData << setprecision(2);

	outData.open("Results.txt");
	outData << "                  Clothing Size" << endl << endl;
	outData << setw(5) << "ID" << setw(10) << "Height" << setw(10) << "Weight" << setw(10) << "Age" << setw(10) << "Hat Size" << setw(10) << "Jacket Size" <<setw(10)<< "Pant Size"<< endl;

	for (int i = 0; i<3; i++)
	{
		outData << setw(5) << id[i] << setw(10) << height[i] << setw(10) << weight[i] << setw(10) << hatSize[i] << setw(10) << jacketSize[i] << setw(10) << pantSize[i] << endl;
	}
	return;
}
