package Data_Structures.Hash_Tables;

/**
 * Класс реализует простейший элемент для хранения данных и чтения их.
 */
public class DataItem {
    private int iData;
    DataItem(int iData){
        this.iData = iData;
    }
    public int getKey(){
        return this.iData;
    }
}
