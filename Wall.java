import java.awt.*;

public class Wall{

    public int x, y, width, height;

    public Wall(int x, int y, int w, int h){
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
    }

    public boolean intersects(int x, int y){
        
        return (x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height);
    }

    public void display(Graphics g){

        g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }
}
