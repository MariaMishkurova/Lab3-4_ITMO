package Other;
import Exceptions.ImpossibleSizeException;


public class Stuff {
    private Size size;
    private final String name;
    private boolean isHold = false;
    private InWhichHand inWhichHand = InWhichHand.NONE;


    private Stuff(String name){
        this.name = name;
    }
    public Stuff (String name, Size size){
        this(name);
        this.size = size;
    }
    public Stuff (String name, double ft_height){
            this(name);
            if(ft_height <= 0){
                try {
                    throw new ImpossibleSizeException();
                } catch (ImpossibleSizeException e) {
                    System.out.println("\u001B[31mНедопустимый размер. Заменён на 0.01 фут\u001B[0m");
                    ft_height = 0.01;
                }
            }

            if (ft_height < 1) {
                setSize(Size.SMALL);
            } else if (ft_height >=1 && ft_height < 3){
                setSize(Size.MIDDLE);
            } else if (ft_height >=3 && ft_height < 15){
                setSize(Size.BIG);
            } else  setSize(Size.HUGE);
    }
    public void setSize(Size size){
        this.size = size;
    }
    public Size getSize(){
        return size;
    }

    public void setInWhichHand(InWhichHand inWhichHand){
        this.inWhichHand = inWhichHand;
    }
    public InWhichHand getInWhichHand(){
        return inWhichHand;
    }

    public void setIsHold(boolean isHold){
        this.isHold = isHold;
    }
    public boolean getIsHold(){
        return isHold;
    }

    @Override
    public String toString(){
        return name + " (размер " + this.size + ")";
    }

        public enum Size{
            SMALL("небольшой"), MIDDLE("средний"), BIG("большой"), HUGE("огромный");
            final String sizeName;
            Size(String sizeName){
                this.sizeName = sizeName;
            }
            @Override
            public String toString(){
                return sizeName;
            }
        }
            public enum InWhichHand{
                IN_LEFT("держит в левой руке"), IN_RIGHT("держит в правой руке"),
                    IN_BOTH("держит в обоих руках"), NONE("не держит ничего");
                final String describe;
                InWhichHand(String describe){
                    this.describe = describe;
                }
                @Override
                public String toString(){
                    return describe;
                }
        }
}
