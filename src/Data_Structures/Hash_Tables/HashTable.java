package Data_Structures.Hash_Tables;

/**
 *  Простейшая хеш-таблица.
 *  Применино двойное хеширование.
 */
public class HashTable {

    private DataItem[] hashArray;
    public final int SIZE;
    private final static DataItem nonItem = new DataItem(-1);

    public HashTable(int size){
        this.SIZE = size;
        this.hashArray = new DataItem[SIZE];
    }
//******************************************************************************************
    public void displayTable(){
        System.out.println("Table: ");
        for (int i = 0; i < SIZE; i++) {
            if (hashArray[i]!=null){
                System.out.print(hashArray[i].getKey() + " ");
            } else {
                System.out.print("** ");
            }
        }
        System.out.println("");
    }
//******************************************************************************************
    private int hashFunction(int key){
        return key% SIZE;
    }
//******************************************************************************************
    private int hashFunction2(int key){
        return 5-(key%5);
    }
//******************************************************************************************
    public void insert(int data){
        DataItem item = new DataItem(data);
        int hashValue = this.hashFunction(data);
        int step = this.hashFunction2(data);

        while (hashArray[hashValue] !=null && hashArray[hashValue].getKey()!=-1){
            hashValue+=step;
            hashValue %= SIZE;
        }
        hashArray[hashValue]=item;
    }
//******************************************************************************************
    public DataItem delete(int key) {
        int hashVal = this.hashFunction(key);
        int step = this.hashFunction2(key);

        while (hashArray[hashVal]!=null && hashArray[hashVal].getKey()!=-1){
            if (hashArray[hashVal].getKey()==key){
                DataItem temp = hashArray[hashVal];
                hashArray[hashVal] = nonItem;
                return temp;
            }
            hashVal+=step;
            hashVal%= SIZE;
        }
        return null;
    }
//*******************************************************************************************
    public DataItem find(int key){
        int hashVal = this.hashFunction(key);
        int step = this.hashFunction2(key);

        while (hashArray[hashVal]!=null){
            if (hashArray[hashVal].getKey()==key){
                return hashArray[hashVal];
            }
            hashVal+=step;
            hashVal%= SIZE;
        }
        return null;
    }
}

