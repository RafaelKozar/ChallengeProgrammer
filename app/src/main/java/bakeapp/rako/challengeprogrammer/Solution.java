package bakeapp.rako.challengeprogrammer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rako on 05/11/2018.
 */

public class Solution {
    static int evenForest(int t_nodes, int t_edges, List<Integer> t_from, List<Integer> t_to) {

        List<Integer> cont = new ArrayList<>();


        return 0;
    }

    static int busca(List<Integer> t_from, List<Integer> t_to, int num, int contador) {
        for (int i = t_to.size(); i >= 0; i--) {
            if (t_to.get(i) == 1){
               return contador;
               break;
            }

            if (t_to.get(i) == num) {
                contador++;
                t_to.remove(i);
                t_from.remove(i);
                busca(t_from, t_to, num, contador);
                break;
            }
        }

        for(int i = t_from.size(); i >= 0; i--) {
            if (t_from.get(i) == num) {
                contador++;
                t_from.remove(i);
            }
        }

    }

}
