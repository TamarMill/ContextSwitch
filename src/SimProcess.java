//this class simulates a process
public class SimProcess {
    int pid;
    String procName;
    int totalInstructions;
    public SimProcess( int pid, String procName,int total){
        this.pid=pid;
        this.procName=procName;
        this.totalInstructions=total;
    }

    public ProcessState execute(int i){
        var d=Math.random()*100;
        System.out.println("process id: "+ pid+ " name: "+ procName+ " instruction number being executed: "+i);
        //return state of process after executing an instruction
        if(i>=totalInstructions){
            return ProcessState.FINISHED;
        }
//block 15% of the time
        else if(d<15){
            return ProcessState.BLOCKED;
        }
        return ProcessState.READY;
        }

}

