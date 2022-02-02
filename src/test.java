import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {

        SimProcessor processor = new SimProcessor();
        //instantiate 10 processes
        SimProcess process1 = new SimProcess(111, "process1", 230);
        SimProcess process2 = new SimProcess(222, "process2", 200);
        SimProcess process3 = new SimProcess(333, "process3", 199);
        SimProcess process4 = new SimProcess(444, "process4", 300);
        SimProcess process5 = new SimProcess(555, "process5", 130);
        SimProcess process6 = new SimProcess(666, "process6", 214);
        SimProcess process7 = new SimProcess(777, "process7", 398);
        SimProcess process8 = new SimProcess(888, "process8", 245);
        SimProcess process9 = new SimProcess(999, "process9", 368);
        SimProcess process10 = new SimProcess(100, "process10", 346);
        //each process has a PCB
        ProcessControlBlock pcb1 = new ProcessControlBlock(process1);
        ProcessControlBlock pcb2 = new ProcessControlBlock(process2);
        ProcessControlBlock pcb3 = new ProcessControlBlock(process3);
        ProcessControlBlock pcb4 = new ProcessControlBlock(process4);
        ProcessControlBlock pcb5 = new ProcessControlBlock(process5);
        ProcessControlBlock pcb6 = new ProcessControlBlock(process6);
        ProcessControlBlock pcb7 = new ProcessControlBlock(process7);
        ProcessControlBlock pcb8 = new ProcessControlBlock(process8);
        ProcessControlBlock pcb9 = new ProcessControlBlock(process9);
        ProcessControlBlock pcb10 = new ProcessControlBlock(process10);

        final int QUANTUM = 5;
//add all processes to Ready List
        ArrayList<SimProcess> ready = new ArrayList<SimProcess>();
        ready.add(process1);
        ready.add(process2);
        ready.add(process3);
        ready.add(process4);
        ready.add(process5);
        ready.add(process6);
        ready.add(process7);
        ready.add(process8);
        ready.add(process9);
        ready.add(process10);
//arrayList to hold blocked processes
        ArrayList<SimProcess> blocked = new ArrayList<SimProcess>();
        int count=0;
//set current process to the first on the ready list
        processor.setCurrProcess(ready.get(0));
        ProcessState state=ProcessState.READY;
//loop of 3000- each step is either an instruction execution from the current process or a context switch
        for (int i = 0; i < 3000; i++) {
//if all instructions have executed remove process from processor and perform context switch
            if (state == ProcessState.FINISHED) {
                System.out.println("Process Completed");
                ready.remove(processor.getCurrProcess());
                if(ready.isEmpty()&& blocked.isEmpty()){
                    System.out.println("All Processes Completed");
                    break;
                }
                System.out.print("Step: "+i+" ");
                for (ProcessControlBlock processControlBlock : Arrays.asList(pcb1, pcb2, pcb3, pcb4, pcb5, pcb6, pcb7, pcb8, pcb9, pcb10)) {
                    contextSwitchstep1(processControlBlock,processor);
                }
                for (ProcessControlBlock processControlBlock : Arrays.asList(pcb1, pcb2, pcb3, pcb4, pcb5, pcb6, pcb7, pcb8, pcb9, pcb10)) {
                    contextSwitches2(processControlBlock,processor,ready,blocked);
                }
                //ProcessState is ready after a context switch
                state=ProcessState.READY;
                //reset count to 0
                count = 0;


            }
//if process is blocked remove from processor and put onto blocked list and perform context switch
             else if (state == ProcessState.BLOCKED) {
                System.out.println("Process Blocked");
                blocked.add(processor.getCurrProcess());
                ready.remove(processor.getCurrProcess());
                System.out.print("Step: "+i+" ");
                for (ProcessControlBlock processControlBlock : Arrays.asList(pcb1, pcb2, pcb3, pcb4, pcb5, pcb6, pcb7, pcb8, pcb9, pcb10)) {
                    contextSwitchstep1(processControlBlock,processor);
                }
                for (ProcessControlBlock processControlBlock : Arrays.asList(pcb1, pcb2, pcb3, pcb4, pcb5, pcb6, pcb7, pcb8, pcb9, pcb10)) {
                    contextSwitches2(processControlBlock,processor,ready,blocked);
                }
                //reset count to 0
                count=0;
                //ProcessState is ready after a context switch
                state=ProcessState.READY;



            }
             //if one process executes 5 instructions it should be removed from the processor and put back on at the end of the ready list
            else if(count>=QUANTUM){
                //reset count to 0
                count=0;
                ready.remove(processor.getCurrProcess());
                ready.add(processor.getCurrProcess());
                System.out.println("Quantum Expired");
                System.out.print("Step: "+i+" ");
                for (ProcessControlBlock processControlBlock : Arrays.asList(pcb1, pcb2, pcb3, pcb4, pcb5, pcb6, pcb7, pcb8, pcb9, pcb10)) {
                    contextSwitchstep1(processControlBlock,processor);
                }
                for (ProcessControlBlock processControlBlock : Arrays.asList(pcb1, pcb2, pcb3, pcb4, pcb5, pcb6, pcb7, pcb8, pcb9, pcb10)) {
                    contextSwitches2(processControlBlock,processor,ready,blocked);
                }
                //ProcessState is ready after a context switch
                state=ProcessState.READY;

            }
            //if no context switch is performed execute the next instruction
            else {
                System.out.print("Step: "+i+" ");
                state = processor.executeNextInstruction();


                count++;
            }
            wakeUp(blocked,ready);
        }
    }
    //save values from registers of current process and its instruction number
public static void contextSwitchstep1(ProcessControlBlock pcb, SimProcessor processor){
    if (pcb.getProcess()==processor.getCurrProcess()){
        pcb.setRegister1(processor.getRegister1());
        pcb.setRegister2(processor.getRegister2());
        pcb.setRegister3(processor.getRegister3());
        pcb.setRegister4(processor.getRegister4());
        pcb.setCurrentInstruction(processor.getCurrInstruction());
        int cur= processor.getCurrInstruction();
        System.out.println("Context switch: Saving process: " +processor.getCurrProcess().procName+ " Instruction: "+cur+" R1: "+processor.getRegister1()+" R2: "+processor.getRegister3()+ " R3: "+processor.getRegister4());
    }

}
//restore values of registers and instruction number for the new process on the processor
public static void contextSwitches2(ProcessControlBlock pcb, SimProcessor processor, ArrayList a,ArrayList b) {

        if(a.isEmpty()&!b.isEmpty())   {
                        System.out.println("Processor is idling");

        while(a.isEmpty() &! b.isEmpty()){

                    wakeUp(b,a);
                }    }
        if(!a.isEmpty()){
        processor.setCurrProcess((SimProcess) a.get(0));}


    if (pcb.getProcess() == processor.getCurrProcess()) {
        processor.setRegister1(pcb.getRegister1());
        processor.setRegister2(pcb.getRegister2());
        processor.setRegister3(pcb.getRegister3());
        processor.setRegister4(pcb.getRegister4());
        processor.setCurrInstruction(pcb.getCurrentInstruction());
        int cur= processor.getCurrInstruction();
        System.out.println("Restoring process: " +processor.getCurrProcess().procName+ " Instruction: "+cur +" R1: "+processor.getRegister1()+" R2: "+processor.getRegister3()+ " R3: "+processor.getRegister4());

    }
}
//after each step loop through everything on the blocked list and wake it with 30% probability
public static void wakeUp(ArrayList block, ArrayList ready)  {
        for(int i = 0; i < block.size(); i++){
           var d=Math.random()*100;

          if (d<30) {

              ready.add(block.get(i));

          }}
        //remove anything that has joined the ready list from the blocked list
        block.removeAll(ready);

    }}
