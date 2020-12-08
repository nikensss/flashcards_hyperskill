class Problem {

    public static void main(String[] args) {
        // Write your code here
        NamedParameter[] namedParameters = new NamedParameter[args.length / 2];
        for (int i = 0; i < args.length; i += 2) {
            namedParameters[i / 2] = new NamedParameter(args[i], args[i + 1]);
        }

        for (NamedParameter p : namedParameters) {
            System.out.println(p.toString());
        }
    }
}

class NamedParameter {
    private final String name;
    private final String value;

    NamedParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s=%s", this.name, this.value);
    }
}