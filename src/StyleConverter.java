public class StyleConverter {
    public static String convertStyles(String style){
        return switch (style) {
            case ("Flag, Green") -> "#green";
            case ("Drinking Water") -> "#blue";
            default -> "#white";
        };
    }
}
