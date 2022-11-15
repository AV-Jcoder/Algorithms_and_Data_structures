package Data_Structures.Hash_Tables;

/**
 * хеш таблица
 * метод цепочек
 * хеш функция по формуле Горнера.
 */
public class HashTableLinkedList {

    private LinkList[] list;
    private final int SIZE;

    public HashTableLinkedList(int size){
        this.SIZE = size;
        this.list = new LinkList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            list[i] = new LinkList();
        }
    }
//********************************************
    // Хорошая хеш-функция
    public int hash(int data){
        //
        int hashValue = 0;
        int maxDigits = getDigit(data);
        int divider = 10;
        for (int i = 0; i < maxDigits; i++){
            hashValue = ((hashValue*10)+(data%divider))%SIZE;
            divider *=10;
        }
        return hashValue;
        //return data%SIZE;
    }
//********************************************
    public void insert(int key){
        int index = this.hash(key);
        list[index].insert(key);
    }
//********************************************
    public void delete(int key){
        int hashIndex = this.hash(key);
        list[hashIndex].delete(key);
    }
//********************************************
    public void showTable(){
        for (int i = 0; i < list.length; i++) {
            System.out.printf("Bucket [#%d]---",i);
            this.list[i].showList();
            System.out.println(" ");
        }
    }
//********************************************
    private static int getDigit(int num) {
        int digit = 1;
        int divider = 10;
        while (num >= divider) {
            digit++;
            divider *= 10;
        }
        return digit;
    }
//********************************************
//********************************************



}
