public class Lab01ErrorEstimation {
    public static void main(String[] args) {
        // Исходные данные
        double X = 0.9538;
        double a = 7.0345;
        double b = 0.231;
        double c = 0.6572;

        // 1. Округление числа X до трех значащих цифр
        double X1 = roundToSignificantFigures(X, 3);
        double absoluteError = Math.abs(X - X1);
        double relativeError = absoluteError / Math.abs(X);

        // Вывод результатов для X
        System.out.printf("X1 ≈ %.4f (округлено до трех значащих цифр)%n", X1);
        System.out.printf("Предельная абсолютная погрешность: %.4e%n", absoluteError);
        System.out.printf("Предельная относительная погрешность: %.4e%n", relativeError);

        // 2. Вычисление значения Z при заданных значениях a, b и c
        double Z = calculateZ(a, b, c);

        // Вывод результата для Z
        System.out.printf("Z = %.6f%n", Z);
    }

    // Метод для округления числа до заданного количества значащих цифр
    public static double roundToSignificantFigures(double num, int n) {
        if (num == 0) {
            return 0;
        }

        final double d = Math.ceil(Math.log10(num < 0 ? -num : num));
        final int power = n - (int) d;

        final double magnitude = Math.pow(10, power);
        final long shifted = Math.round(num * magnitude);

        return shifted / magnitude;
    }

    // Метод для вычисления Z
    public static double calculateZ(double a, double b, double c) {
        return Math.log(a + 4 * b) / (a * b - c);
    }
}
