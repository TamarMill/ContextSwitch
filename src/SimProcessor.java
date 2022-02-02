import java.util.Random;

public class SimProcessor {

    SimProcess currProcess;


    public SimProcess getCurrProcess() {
        return currProcess;
    }
    public void setCurrProcess(SimProcess currProcess) {
         this.currProcess=currProcess;
    }

    int register1;
    int register2;
    int register3;
    int register4;

    public void setRegister1(int register1) {
        this.register1 = register1;
    }

    public void setRegister2(int register2) {
        this.register2 = register2;
    }

    public void setRegister3(int register3) {
        this.register3 = register3;
    }

    public void setRegister4(int register4) {
        this.register4 = register4;
    }

    public int getRegister1() {
        return register1;
    }

    public int getRegister2() {
        return register2;
    }

    public int getRegister3() {
        return register3;
    }

    public int getRegister4() {
        return register4;
    }

    int currInstruction=1;

    public int getCurrInstruction() {
        return currInstruction;
    }

    public void setCurrInstruction(int currInstruction) {

        this.currInstruction = currInstruction;
    }
    //calls the execute method and passes in the value of the process's current instruction
    public ProcessState executeNextInstruction(){
        ProcessState state=currProcess.execute(currInstruction);
        currInstruction++;
        Random random=new Random();
        //set the four registers to random values to simulate the resulting state of the instruction's execution
        register1= random.nextInt();
        register2= random.nextInt();
        register3= random.nextInt();
        register4= random.nextInt();
        return state;
    }
}
