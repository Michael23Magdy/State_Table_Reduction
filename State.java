import java.lang.Math;

public class State {
    private int index;
    private int[] nextStates;
    private Boolean[] outputs;
    private int numInputs;
    private int size;
    private int numOutputs;

    public static final String thinYellowLine = "  "+ ConsoleColors.YELLOW_BACKGROUND +" "+ ConsoleColors.RESET +"  ";
    public static final String thickYellowLine = "  "+ ConsoleColors.YELLOW_BACKGROUND +"  "+ ConsoleColors.RESET +"  ";

    public State(int index, int numInputs, Boolean isMealy){
        this.index = index;
        this.numInputs = numInputs;
        this.size = (int)Math.pow(2, numInputs);
        nextStates = new int[size];
        for(int i = 0; i<size; i++)
            nextStates[i]=-1;
        numOutputs = isMealy?size:1;
        outputs = new Boolean[numOutputs];
    }

    public void setNextState(int inputIndex, int nexState){
        nextStates[inputIndex] = nexState;
    }

    public void setOutput(int inputIndex, Boolean output){
        outputs[inputIndex] = output;
    }

    public int getNextState(int inputIndex){
        return nextStates[inputIndex];
    }

    public Boolean getOutput(int inputIndex){
        return outputs[inputIndex];
    }

    public int getIndex(){
        return index;
    }

    public int size(){
        return size;
    }

    public Boolean[] getOutputs() {
        return outputs;
    }

    public int getNumOutputs(){
        return numOutputs;
    }

    public Boolean outputEquavalent(State state){
        for(int i = 0; i<numOutputs;i++)
            if (this.getOutput(i) != null && state.getOutput(i) != null && this.getOutput(i) != state.getOutput(i)) 
                return false;

        return true;
    }

    public Boolean nextStateEquavalent(State state,int i){
        if (this.getNextState(i) != -1 && state.getNextState(i) != -1 && this.getNextState(i) != state.getNextState(i)) 
            return false;
        return true;
    }

    public void printState(){
        
        System.out.print( thickYellowLine + NumberSystemConverter.decimalToExcel(this.getIndex()) +  thickYellowLine);
        for(int i = 0; i < this.size(); i++){
            if (this.getNextState(i)==-1)
                System.out.print("-");
            else
                System.out.print(NumberSystemConverter.decimalToExcel(this.getNextState(i)));
            if (i!=size-1)
                System.out.print(thinYellowLine);
        }
        System.out.print(thickYellowLine);
        for(int i = 0; i < numOutputs; i++){
            if (this.getOutput(i)==null)
                System.out.print("-");
            else
                System.out.print((char)NumberSystemConverter.BooltoBit(this.getOutput(i)));
            
            if (i!=numOutputs-1)
                System.out.print(thinYellowLine);
        }
        System.out.print(thickYellowLine);
        System.out.println();
    }

    public static void main(String[] args) {
        State s = new State(0, 1, true);
        s.setNextState(0, 2);
        s.setNextState(1, 0);
        s.setOutput(0, true);
        s.setOutput(1, false);
        s.printState();
    }
}
