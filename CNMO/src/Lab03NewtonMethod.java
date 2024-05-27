public class Lab03NewtonMethod {
    public static void main(String[] args) {
        // Начальное приближение
        double x0 = 1.0;

        // Допустимая погрешность для сходимости
        double tolerance = 1e-6;

        // Максимальное количество итераций
        int maxIterations = 1000;

        // Постоянный шаг для численного дифференцирования
        double h = 1e-5;

        double root = newtonMethod(x0, tolerance, maxIterations, h);

        // Печать найденного корня
        System.out.printf("\nНайденный корень: x = %.9f%n", root);
    }

    // Определение функции f(x)
    public static double f(double x) {
        return Math.exp(-0.7 * x) - 0.3 * Math.sqrt(x) + 1;
    }

    // Численное дифференцирование для f'(x)
    public static double df(double x, double h) {
        return (f(x + h) - f(x)) / h;
    }

    // Реализация метода Ньютона
    public static double newtonMethod(double x0, double tolerance, int maxIterations, double h) {
        double x = x0;

        for (int iter = 0; iter < maxIterations; iter++) {
            double fx = f(x);
            double dfx = df(x, h);

            if (Math.abs(dfx) < 1e-10) {
                System.out.println("Производная близка к нулю, метод Ньютона не может продолжаться.");
                break;
            }

            double x1 = x - fx / dfx;

            // Вывод текущих значений
            System.out.printf("Итерация %d: x = %.9f, f(x) = %.9f%n", iter + 1, x1, f(x1));

            if (Math.abs(x1 - x) < tolerance) {
                System.out.println("\nСошлось после " + (iter + 1) + " итераций.");
                return x1;
            }

            x = x1;
        }

        System.out.println("Максимальное количество итераций достигнуто без сходимости.");
        return x;
    }
}
