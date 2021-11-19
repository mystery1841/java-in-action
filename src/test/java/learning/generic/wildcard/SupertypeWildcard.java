package learning.generic.wildcard;

import learning.generic.Pair;
import learning.oop.inherit.Manager;

public class SupertypeWildcard {

    public static void minmaxBonus(Manager[] a, Pair<? super Manager> result)
    {
        if (a.length == 0) return;
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if (min.getBonus() > a[i].getBonus()) min = a[i];
            if (max.getBonus() < a[i].getBonus()) max = a[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }

}
