package todo;

public class AddTask {
    private String task;
    private String date;
    private String notes;
    private boolean completed;
    
    public AddTask(String task, String date, String notes, boolean completed){
        this.task = task;
        this.date = date;
        this.notes = notes;
        this.completed = completed;
    }

    public String getTask() {
        return task;
    }

    public String getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isCompleted() {
        return completed;
    }
    
    
    
    
}
