public class NumberSystemConverter {
    
    
    // Method to convert a decimal number to an Excel-style alphabetic column label
    public static String decimalToExcel(int columnNumber) {
        StringBuilder result = new StringBuilder();
        columnNumber++;
        while (columnNumber > 0) {
            int remainder = (columnNumber - 1) % 26;
            result.insert(0, (char) (remainder + 'A'));
            columnNumber = (columnNumber - 1) / 26;
        }
        
        return result.toString();
    }
    
    // Method to convert an Excel-style alphabetic column label to a decimal number
    public static int excelToDecimal(String columnLabel) {
        if(columnLabel.equals("-")) return -1;
        int result = 0;
        for (int i = 0; i < columnLabel.length(); i++) {
            result = result * 26 + (columnLabel.charAt(i) - 'A' + 1);
        }
        return result-1;
    }
    public static char BooltoBit(Boolean x){
        if(x==null) return '-';
        return x?'1':'0';
    }
    // Main method for testing
    public static void main(String[] args) {
        // Testing decimalToExcel method
        int decimalNumber = 27;
        String excelLabel = decimalToExcel(decimalNumber);
        System.out.println("Excel label for " + decimalNumber + ": " + excelLabel);
        
        // Testing excelToDecimal method
        String excelLabelTest = "AB";
        int decimalNumberTest = excelToDecimal(excelLabelTest);
        System.out.println("Decimal number for " + excelLabelTest + ": " + decimalNumberTest);
    }

    
}
