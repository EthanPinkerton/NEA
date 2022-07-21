package game;

public class Chunk {

    protected String[][] chunk = new String[9][9];
    protected int x,y;

    public Chunk(int x, int y) {
        this.x = x;
        this.y = y;
        if(x == 0 && y == 0){
            this.chunk = first();
        }else {
            this.chunk = generate();
        }
    }

    private String[][] first(){
        String[] e = {"x","x","x","x","o","x","x","x","x"};
        String[] m = {"x","o","o","o","o","o","o","o","x"};
        return new String[][]{e,m,m,m,m,m,m,m,e};
    }

    private String[][] generate(){
        String[] e = {"x","x","x","x","o","x","x","x","x"};
        String[] m = {"x","o","o","o","o","o","o","o","x"};
        return new String[][]{e,m,m,m,m,m,m,m,e};
    }

    public String[][] getChunk(){
        return chunk;
    }

    public String getCoord(int x,int y){
        return chunk[y][x];
    }
}
