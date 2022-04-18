package com.movsisyan.module;


import java.util.Arrays;
import java.util.Collections;

public class Fraction implements Comparable<Fraction> {
    private int a;
    private int b;

    public Fraction() {
        this.a = 0;
        this.b = 1;
    }

    public Fraction(Fraction f) {
        this.a = f.a;
        this.b = f.b;
    }

    public Fraction(int a) {
        this.a = a;
        this.b = 1;
    }

    public Fraction(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("b can't be 0");
        }
        this.a = a;
        this.b = b;
        reduce();
    }

    public void setA(int a) {
        this.a = a;
        reduce();
    }

    public void setB(int b) {
        this.b = b;
        reduce();
    }

    /**
     * input Fraction(String)
     *
     * @param s
     */
    public Fraction(String s) {
        String[] split = s.split("[ /]");
        this.a = Integer.parseInt(split[0]);
        this.b = 1;
        if (split.length == 2) {
            this.b = Integer.parseInt(split[1]);
            if (this.b == 0)
                throw new ArithmeticException("b can't be a zero");
            reduce();
        }
    }

    public static int GCD(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        int gcd = 1;
        for (int i = 1; i <= a; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    /**
     * Реализуйте закрытый метод reduce, который приводит дробь к каноническому представлению.
     * У каждой дроби существует единственное каноническое представление.
     * Каноническое представление – это такое представление a/b, что b>0, НОД(a,b)=1.
     *
     * @return int
     */
    public void reduce() {
        if (b < 0) {
            a *= -1;
            b *= -1;
        }
        int gcd = GCD(this.a, this.b);
        this.a /= gcd;
        this.b /= gcd;
    }

    /**
     * •	Переопределите метод toString таким образом, чтобы он возвращал строковое представление объекта в формате:
     * •	a/b, если a и b не равны 0
     * •	0, если а равно 0
     * •	а, если b равно 1
     *
     * @return
     */
    @Override
    public String toString() {
        if (a == 0) {
            return "0";
        }
        if (b == 1) {
            return a + "";
        }
        return a + "/" + b;
    }

    @Override
    public int compareTo(Fraction o) {
        return Integer.compare(this.a * o.b, o.a * o.b);
    }

    public int compareTo(double a) {
        return Double.compare((double) this.a / this.b, a);
    }

    public int compareTo(int a) {
        return this.compareTo(new Fraction(a));
    }

    /**
     * •	Определите операции сложения, вычитания, умножения так, чтобы можно было складывать:
     * •	Две дроби (результатом является Fraction)
     * •	Дробь и int (результатом является Fraction)
     * •	Дробь и double (результатом является double)
     */
    public Fraction addition(Fraction other) {
        return new Fraction(this.a * other.b + other.a * this.b, this.b * other.b);
    }

    public Fraction addition(int a) {
        return this.addition(new Fraction(a));
    }

    public double addition(double a) {
        return (double) this.a / this.b + a;
    }

    public Fraction subtraction(Fraction other) {
        return new Fraction(this.a * other.b - other.a * this.b, this.b * other.b);
    }

    public double subtraction(double a) {
        return (double) this.a / this.b - a;
    }

    public Fraction subtraction(int a) {
        return this.subtraction(new Fraction(a));
    }

    public Fraction multiplication(Fraction other) {
        return new Fraction(this.a * other.a, this.b * other.b);
    }

    public double multiplication(double a) {
        return (double) this.a * a / this.b;
    }

    public Fraction multiplication(int a) {
        return this.multiplication(new Fraction(a));
    }

    /**
     * Определить операцию получения обратной дроби
     */
    public Fraction inverse() {
        return new Fraction(this.b, this.a);
    }

    public Fraction division(Fraction o) {
        return this.multiplication(o.inverse());
    }

    /**
     * Определите операции +=, -=, *=, /= для случая, когда правый операнд имеет тип Fraction
     */
    public Fraction plusEquals(Fraction other) {
        Fraction res = this.addition(other);
        this.a = res.a;
        this.b = res.b;
        return this;
    }

    public Fraction minusEquals(Fraction other) {
        Fraction res = this.subtraction(other);
        this.a = res.a;
        this.b = res.b;
        return this;
    }

    public Fraction multiEquals(Fraction other) {
        Fraction res = this.multiplication(other);
        this.a = res.a;
        this.b = res.b;
        return this;
    }

    public Fraction divisionEquals(Fraction other) {
        Fraction res = this.division(other);
        this.a = res.a;
        this.b = res.b;
        return this;
    }

    /**
     * Реализуйте метод sum, принимающий на вход массив дробей и производящий их общее сложение
     */
    public Fraction sum(Fraction... fractions) {
        Fraction sumOfFraction = new Fraction();
        for (Fraction fraction : fractions) {
            sumOfFraction.plusEquals(fraction);
        }
        return sumOfFraction;
    }

    /**
     * Реализуйте метод maxFraction, принимающий на вход массив дробей и находящий
     * максимальную дробь в данном массиве
     */
    public Fraction max(Fraction... fractions) {
        Fraction maxFraction = new Fraction(Integer.MIN_VALUE);
        for (Fraction fraction : fractions) {
            if (fraction.compareTo(maxFraction) > 0) {
                maxFraction = fraction;
            }
        }
        return maxFraction;
    }

    /**
     * Произвести сортировку массива дробей в порядке возрастания и в порядке убывания
     */
    public void sort(Fraction... others) {
        Arrays.sort(others);
    }

    public void reverseSort(Fraction... others) {
        Arrays.sort(others, Collections.reverseOrder());
    }
}
