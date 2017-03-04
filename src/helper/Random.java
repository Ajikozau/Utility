package helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Random
extends java.util.Random {
    private static final long serialVersionUID = 3049695947451276476L;
    private static Random instance = null;
    private final Pattern guessPattern = Pattern.compile("\\s*(\\d+)?\\s*(?:([:])\\s*(\\d+))??\\s*(?:([d:])\\s*(\\d+))?\\s*(?:([+-/*])\\s*(\\d+))?\\s*");

    public static Random getInstance() {
        if (instance == null) {
            instance = new Random();
        }
        return instance;
    }

    public Random() {
    }

    public Random(long arg0) {
        super(arg0);
    }

    public int bestOf(int num, int outof, int sides) {
        if (num > outof) {
            int t = num;
            num = outof;
            outof = t;
        }
        List<Integer> arr = new ArrayList<>(outof);
        int ret = 0;
        while (outof > 0) {
            arr.add(this.dice(1, sides));
            --outof;
        }
        Collections.sort(arr);
        while (num > 0) {
            ret += (arr.get(arr.size() - 1));
            --num;
        }
        return ret;
    }

    public int bestOf(int num, List<Integer> group) {
        int outof = group.size();
        if (num > outof) {
            throw new IllegalArgumentException();
        }
        ArrayList<Integer> arr = new ArrayList<>(group);
        int ret = 0;
        Collections.sort(arr);
        while (num > 0) {
            ret += arr.get(arr.size() - 1);
            --num;
        }
        return ret;
    }

    public int bestOf(int num, int outof, String g) {
        if (num > outof) {
            int t = num;
            num = outof;
            outof = t;
        }
        ArrayList<Integer> arr = new ArrayList<>(outof);
        int ret = 0;
        while (outof > 0) {
            arr.add(this.guess(g));
            --outof;
        }
        Collections.sort(arr);
        while (num > 0) {
            ret += (arr.get(arr.size() - 1));
            --num;
        }
        return ret;
    }

    public <T> T choice(List<T> c) {
        for (Class cls : c.getClass().getInterfaces()) {
            if (!cls.equals(RandomAccess.class)) continue;
            T ret = c.get((int)(this.nextFloat() * (float)c.size()));
            return ret;
        }
        return this.choice((Collection<T>)c);
    }

    public <T> T choice(Collection<T> c) {
        Object[] a = c.toArray();
        Object ret = a[(int)(this.nextFloat() * (float)a.length)];
        return (T)ret;
    }

    public int dice(int num, int sides) {
        int ret = 0;
        while (num > 0) {
            ret += this.nextInt(sides) + 1;
            --num;
        }
        return ret;
    }

    public List<Integer> diceList(int num, int sides) {
        ArrayList<Integer> ret = new ArrayList<>(num);
        for (int c = 0; c < num; ++c) {
            ret.add(this.nextInt(sides) + 1);
        }
        return ret;
    }

    public int guess(String str) {
        Matcher mat = this.guessPattern.matcher(str);
        int ret = 0;
        if (mat.matches()) {
            int p;
            String num1 = mat.group(1);
            String wmode = mat.group(2);
            String wnum = mat.group(3);
            String mode = mat.group(4);
            String num2 = mat.group(5);
            String pmode = mat.group(6);
            String pnum = mat.group(7);
            int a = num1 == null ? 0 : Integer.parseInt(num1);
            int b = num2 == null ? 0 : Integer.parseInt(num2);
            int w = wnum == null ? 0 : Integer.parseInt(wnum);
            int n = p = pnum == null ? 0 : Integer.parseInt(pnum);
            if (num1 != null && num2 != null) {
                if (wnum != null) {
                    if (":".equals(wmode) && "d".equals(mode)) {
                        ret = this.bestOf(a, w, b);
                    }
                } else if ("d".equals(mode)) {
                    ret = this.dice(a, b);
                } else if (":".equals(mode)) {
                    ret = this.nextInt(a, b + 1);
                }
            } else if (num1 != null) {
                ret = ":".equals(wmode) ? this.nextInt(a, w + 1) : a;
            } else if (num2 != null) {
                if (mode != null) {
                    switch (mode) {
                        case "d": {
                            ret = this.dice(1, b);
                            break;
                        }
                        case ":": {
                            ret = this.nextInt(0, b + 1);
                        }
                    }
                }
            } else if (":".equals(wmode)) {
                ret = this.nextInt(0, w + 1);
            }
            if (pmode != null) {
                switch (pmode) {
                    case "+": {
                        ret += p;
                        break;
                    }
                    case "-": {
                        ret -= p;
                        break;
                    }
                    case "*": {
                        ret *= p;
                        break;
                    }
                    case "/": {
                        ret /= p;
                    }
                }
            }
        }
        return ret;
    }

    public int nextInt(int bottom, int top) {
        int diff = top - bottom;
        if (diff == 0) {
            return bottom;
        }
        return bottom + this.nextInt(diff);
    }

    public <T> void shuffle(List<T> list) {
        Collections.shuffle(list, this);
    }
}

