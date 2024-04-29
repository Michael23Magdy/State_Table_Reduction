import java.util.Scanner;

public class Main {
    public static StateTable readStateTableProperties(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number Of State: ");
        int numStates = Integer.parseInt(sc.nextLine());
        System.out.print("Enter Number Of Inputs: ");
        int numInputs =  Integer.parseInt(sc.nextLine());
        System.out.print("Is it a Circuit Mealy? (1 => YES / Anything else => No): ");
        int type =  Integer.parseInt(sc.nextLine());
        Boolean isMealy;
        if (type == 1) {
            isMealy = true;
        } else {
            isMealy = false;
        }
        //sc.close();
        return new StateTable(numStates, numInputs, isMealy);
    }

    public static void clearConsole() {
        try {
            // For Windows
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            // For Unix-like systems
            else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }

    public static void readRow(StateTable st, String query, int size, int numStates, int numOutputs){
        String[] inputs = query.split("\\|");
        if(inputs.length != 3){
            System.out.println("Invalid Input 111111");
            return;
        }
        int index = NumberSystemConverter.excelToDecimal(inputs[0].replaceAll("\\s", ""));
        
        String[] nextStates_str = inputs[1].trim().split("\\s+");
        if (nextStates_str.length != size || index >= numStates) {
            System.out.println("Invalid Input 222222");
            return;
        }
        int[] nextStates = new int[size];
        for(int i = 0; i < size ; i++){
            int tmp = NumberSystemConverter.excelToDecimal(nextStates_str[i]);
            if(tmp >= numStates){
                System.out.println("Invalid Input 555555");
                return;
            }
            nextStates[i] = tmp;
        }
        
        String[] outputs_str = inputs[2].trim().split("\\s+");
        if (outputs_str.length != numOutputs) {
            System.out.println("Invalid Input 33333333");
            return;
        }

        Boolean[] outputs = new Boolean[numOutputs];
        for(int i = 0; i < numOutputs ; i++){
            if (outputs_str[i].equals("1")) {
                outputs[i]=true;
            } else if(outputs_str[i].equals("0")){
                outputs[i]=false;
            } else {
                System.out.println("Invalid Input 4444444");
                return;
            }
        }
        st.setRow(index, nextStates, outputs);
    }

    public static void main(String[] args) {
        clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Implication Table App" + ConsoleColors.RESET);
        StateTable stateTable = readStateTableProperties();
        Scanner sc = new Scanner(System.in);

        String query = new String();
        while (true) {
            clearConsole();
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Implication Table App" + ConsoleColors.RESET);
            stateTable.printTable();
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Enter State in this form: "+ ConsoleColors.YELLOW +"{PS} | {NS0 NS1 ...} | {Z0 Z1 ...} ----- or -1 to submit"+ ConsoleColors.RESET);
            query = sc.nextLine();
            if (query.equals("-1")) {
                break;
            } else {
                readRow(stateTable, query, stateTable.getSize(),stateTable.getNumStates(),stateTable.getNumOutputs());
            }
        }
        clearConsole();
        System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "Implication Table App" + ConsoleColors.RESET);
        stateTable.printTable();
        StateTable reducedTable = ImplicationTable.reduce(stateTable);
        reducedTable.printTable();
    }
}
