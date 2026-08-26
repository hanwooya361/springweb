package example.test.model.dto;

public class ListDto {
    private int no;
    private String pn;
    private int num;
    public ListDto() {
    }
    public ListDto(int no, String pn, int num) {
        this.no = no;
        this.pn = pn;
        this.num = num;
    }
    public int getNo() {
        return no;
    }
    public void setNo(int no) {
        this.no = no;
    }
    public String getPn() {
        return pn;
    }
    public void setPn(String pn) {
        this.pn = pn;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    @Override
    public String toString() {
        return "ListDto [no=" + no + ", pn=" + pn + ", num=" + num + "]";
    }
    
}
