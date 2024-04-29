public class StateTable {
    State[] rows;
    int numStates;
    int numInputs;
    int size;
    int numOutputs;
    Boolean isMealy;

    public int getNumStates(){
        return numStates;
    }

    public int getNumInputs() {
        return numInputs;
    }

    public int getNumOutputs() {
        return numOutputs;
    }

    public int getSize() {
        return size;
    }

    public Boolean getIsMealy() {
        return isMealy;
    }

    public StateTable(int numStates, int numInputs, Boolean isMealy){
        this.numStates = numStates;
        rows = new State[numStates];
        for(int i=0;i<numStates;i++)
            rows[i] = new State(i, numInputs, isMealy);
        this.numInputs = numInputs;
        this.size = (int)Math.pow(2, numInputs);
        this.isMealy = isMealy;
        numOutputs = isMealy?size:1;
    }

    public State getRow(int index) {
        return rows[index];
    }

    public void setRow(int index, int[] nextStates, Boolean[] outputs){
        for(int i=0; i < size; i++)
            rows[index].setNextState(i, nextStates[i]);
        for(int i=0; i < numOutputs; i++)
            rows[index].setOutput(i, outputs[i]);
    }

    private void printLine(int length){
        System.out.print("  "+ConsoleColors.YELLOW_BACKGROUND+"           "+ConsoleColors.RESET);
        for(int i = 0; i < length;i++)
            System.out.print(ConsoleColors.YELLOW_BACKGROUND+"      "+ConsoleColors.RESET);
        System.out.println();
    }
    public void printTable(){
        printLine(size+numOutputs);
        for(int i=0;i<numStates;i++){
            rows[i].printState();
        }
        printLine(size+numOutputs);
    }

    public static void main(String[] args) {
        StateTable tp = new StateTable(4, 2, true);
        tp.setRow(0, new int[]{1,2,-1,4}, new Boolean[]{false,false,true,true});
        tp.printTable();
    }
}
