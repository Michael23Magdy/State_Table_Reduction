public class ImplicationTable {
    public static StateTable reduce(StateTable stateTable){
        int inputs = stateTable.getSize();
        int n = stateTable.getNumStates();

        // implication table Creation
        // --- filling using outputs
        Boolean[][] implicationTable = new Boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j = i;j<n;j++){
                implicationTable[i][j] = stateTable.getRow(i).outputEquavalent(stateTable.getRow(j));
                implicationTable[j][i] = implicationTable[i][j];
            }
        }

        // --- filling using next states
        Boolean change = true;
        while (change) {
            change =false;
            for(int i=0;i<n;i++){
                for(int j = i+1;j<n;j++){
                    if(implicationTable[i][j]){
                        for(int k=0; k<inputs;k++){
                            if (!stateTable.getRow(i).nextStateEquavalent(stateTable.getRow(j), k)) {
                                int i_new = stateTable.getRow(stateTable.getRow(i).getNextState(k)).getIndex();
                                int j_new = stateTable.getRow(stateTable.getRow(j).getNextState(k)).getIndex();
                                if(!implicationTable[i_new][j_new]){
                                    change = true;
                                    implicationTable[i][j] = false;
                                    implicationTable[j][i] = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        

        // print Impication table
        for(int i=0;i<n;i++){
            for(int j = 0;j<i;j++){
                System.out.print(NumberSystemConverter.BooltoBit(implicationTable[i][j]));
            }
            System.out.println();
        }
        System.out.println();

        // creating reduced state table
        String[] imptp = new String[n];
        for(int i=0;i<n;i++){
            imptp[i] = booleanArrayToBinaryString(implicationTable[i]);
        }

        // replacing grouped states with new states
        int newNumStates = 0;
        int[] map = new int[n];
        for(int i=0;i<n;i++) map[i]=-1;
        for(int i=0;i<n;i++){
            if(map[i]==-1){
                map[i]=newNumStates;
                
                System.out.print("{"+NumberSystemConverter.decimalToExcel(i));
                for(int j=i+1;j<n;j++){
                    if(imptp[i].equals(imptp[j])){
                        System.out.print(" ");
                        System.out.print(NumberSystemConverter.decimalToExcel(j));
                        map[j]=newNumStates;
                    }
                }
                System.out.println("} = "+ NumberSystemConverter.decimalToExcel(newNumStates));
                newNumStates++;
            }
        }
        System.out.println();

        // remove redundant states
        StateTable reduced = new StateTable(newNumStates, stateTable.getNumInputs(), stateTable.getIsMealy());
        int cnt = 0; 

        Boolean[] taken = new Boolean[newNumStates];
        for(int i=0;i<newNumStates;i++) taken[i] = false;
        for(int i=0;i<n;i++){
            if(!taken[map[i]]){
                int[] newNextStates = new int[inputs];
                for(int j=0;j<inputs;j++){
                    int val = stateTable.getRow(i).getNextState(j);
                    newNextStates[j] = (val==-1)?-1:map[val];
                }
                reduced.setRow(cnt, newNextStates, stateTable.getRow(i).getOutputs());
                cnt++;
                taken[map[i]]=true;
            }
        }

        return reduced;
    }

    public static String booleanArrayToBinaryString(Boolean[] array) {
        StringBuilder stringBuilder = new StringBuilder();

        // Append '1' for true, '0' for false
        for (boolean b : array) {
            stringBuilder.append(b ? '1' : '0');
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        StateTable tp = new StateTable(9, 1, false);
        tp.setRow(0, new int[]{4,4}, new Boolean[]{true});
        tp.setRow(1, new int[]{2,4}, new Boolean[]{true});
        tp.setRow(2, new int[]{8,7}, new Boolean[]{false});
        tp.setRow(3, new int[]{7,0}, new Boolean[]{true});
        tp.setRow(4, new int[]{8,5}, new Boolean[]{false});
        tp.setRow(5, new int[]{4,6}, new Boolean[]{false});
        tp.setRow(6, new int[]{7,1}, new Boolean[]{true});
        tp.setRow(7, new int[]{2,3}, new Boolean[]{false});
        tp.setRow(8, new int[]{5,1}, new Boolean[]{true});
        tp.printTable();
        StateTable reducedTable = reduce(tp);
        reducedTable.printTable();
    }
}