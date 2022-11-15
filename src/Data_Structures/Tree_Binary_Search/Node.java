package Data_Structures.Tree_Binary_Search;

public class Node {
    int iData;
    double fData;
    Node leftChild;
    Node rightChild;

    public void displayNode(){
        System.out.printf("{iData=%d, dData=%f, leftChild=%s, rightChild=%s}",iData,fData,leftChild,rightChild);
        System.out.println(" ");
    }

    @Override
    public String toString() {
        return "Node{" +
                "iData=" + iData +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }

    public Node(int iData, double fData) {
        this.iData = iData;
        this.fData = fData;
    }
}
