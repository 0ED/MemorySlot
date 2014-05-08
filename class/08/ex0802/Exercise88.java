import java.lang.ArrayIndexOutOfBoundsException;

class Exercise88 {
    private static final String[] fruits = {"みかん", "ばなな", "りんご", "ちくわ", "いちご"};

    String getFruitName(int id) throws ArrayIndexOutOfBoundsException,NotAFruitException {
            if (id == 3) {
                throw new NotAFruitException();
            }
            return fruits[id];
    }

    public static void main(String[] args) {
        Exercise88 app = new Exercise88();
        int number = Integer.parseInt(args[0]);
        try {
            System.out.println(app.getFruitName(number));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("そんなばなな");
        } catch (NotAFruitException e) {
            System.out.println("くだものちゃうし");
        }
    }
}

