class Exercise87 {
    private static final String[] fruits = {"みかん", "ばなな", "りんご", "ぶどう", "いちご"};

    String getFruitName(int id) throws ArrayIndexOutOfBoundsException {
        return fruits[id];
    }

    public static void main(String[] args) {
        Exercise87 app = new Exercise87();
        int number = Integer.parseInt(args[0]);
        try {
            System.out.println(app.getFruitName(number));
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("範囲外です");
        }
    }
}

