public class Lab02GaussSeidel {
    public static void main(String[] args) {
        // Коэффициенты системы
        double[][] a = {
                {4.503, 0.219, 0.527, 0.396},
                {0.259, 5.121, 0.423, 0.206},
                {0.413, 0.531, 4.317, 0.264},
                {0.327, 0.412, 0.203, 4.851}
        };

        // Константы в правой части уравнений
        double[] b = {0.553, 0.358, 0.565, 0.436};

        // Переменные
        String[] variables = {"x1", "x2", "x3", "x4"};

        // Начальное приближение
        double[] x = {0, 0, 0, 0};

        printSystem(a, b, variables);

        // Допустимая погрешность для сходимости
        double tolerance = 0.001;
        System.out.println("\nПогрешность: " + tolerance + "\n");

        // Максимальное количество итераций
        int maxIterations = 1000;

        gaussSeidel(a, b, x, tolerance, maxIterations);

        // Печать решения
        System.out.println("\nРешение:");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("x%d = %.6f%n", i + 1, x[i]);
        }
    }

    public static void gaussSeidel(double[][] a, double[] b, double[] x, double tolerance, int maxIterations) {
        int n = b.length;
        double[] xOld = new double[n];

        for (int iter = 0; iter < maxIterations; iter++) {
            // Копировать текущие значения в xOld
            System.arraycopy(x, 0, xOld, 0, n);

            // Обновление каждой переменной
            for (int i = 0; i < n; i++) {
                double sum = b[i];

                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum -= a[i][j] * x[j];
                    }
                }

                x[i] = sum / a[i][i];
            }

            // Вывод текущих значений переменных
            System.out.printf("Итерация %d: ", iter + 1);
            for (int i = 0; i < n; i++) {
                System.out.printf("x%d = %.6f ", i + 1, x[i]);
            }
            System.out.println();

            // Проверка на сходимость
            boolean converged = true;
            for (int i = 0; i < n; i++) {
                if (Math.abs(x[i] - xOld[i]) > tolerance) {
                    converged = false;
                    break;
                }
            }

            if (converged) {
                System.out.println("\nСошлось после " + (iter + 1) + " итераций.");
                return;
            }
        }
        System.out.println("Максимальное количество итераций достигнуто без сходимости.");
    }

    // Метод для вывода системы уравнений
    public static void printSystem(double[][] a, double[] b, String[] variables) {
        int numRows = a.length;
        int numCols = a[0].length;

        System.out.println("Изначальная система:");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (j > 0) {
                    System.out.print(" + ");
                }
                System.out.printf("%.3f%s", a[i][j], variables[j]);
            }
            System.out.printf(" = %.3f%n", b[i]);
        }
    }
}
