public class ProcessControlBlock {
    //the PCB contains meta information about a process
    SimProcess process;
    public ProcessControlBlock(SimProcess sim){
        this.process=sim;
    }


    public SimProcess getProcess() {
        return process;
    }

    int currentInstruction=1;

    public int getCurrentInstruction() {
        return currentInstruction;
    }

    public void setCurrentInstruction(int currentInstruction) {

        this.currentInstruction = currentInstruction;
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

}
