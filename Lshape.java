public class Lshape extends Shape{

    public Lshape (int X,int Y){
        super();
        this.locations.set(0, new Tuple(X + Block.getHalfLength(), Y + Block.getHalfLength()));
        this.locations.add(new Tuple(X + Block.getHalfLength(), Y - Block.getHalfLength() * 3));
        this.locations.add(new Tuple(X + Block.getHalfLength() * 3, Y + Block.getHalfLength()));
        this.locations.add(new Tuple(X + Block.getHalfLength()*5 , Y + Block.getHalfLength() ));
        for (int i = 0; i < 3; i++) {
            this.blocks.add(new Block(MainFrame.getShapePen()));
        }
    }
}
