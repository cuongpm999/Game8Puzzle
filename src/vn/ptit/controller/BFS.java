/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.ptit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import vn.ptit.model.State;

/**
 *
 * @author Cuong Pham
 */
public class BFS {

    private Stack<State> st;
    private Integer soBuoc;
    private Integer soLanDuyet;

    public BFS() {
        st = new Stack<>();
        soLanDuyet = 0;
    }

    public void BFS(State stateStart) {
        Queue<State> tapBienO = new LinkedList<>();
        List<State> trangThaiDuocMoRong = new ArrayList<>();
        
        st.clear();
        soLanDuyet = 0;
        
        tapBienO.add(stateStart);

        Set<List<Integer>> visited = new HashSet<>();
        visited.add(stateStart.getList());

        while (!tapBienO.isEmpty()) {
            State trangThaiXemXet = tapBienO.remove();
            List<Integer> t = trangThaiXemXet.getList();
            
            trangThaiDuocMoRong.add(trangThaiXemXet);
            soLanDuyet++;
            
            if (ketThuc(t)) {
                State res = new State();
                res = trangThaiXemXet;
                while (res != null) {
                    st.push(res);
                    res = res.getParent();
                }

                break;
            }

            int i = t.indexOf(9);

            if (i % 3 < 2) {
                List<Integer> x = sangPhai(t, i);
                State trangThaiPhatSinh = new State(x, trangThaiXemXet.getC() + 1,
                        0, trangThaiXemXet, "Sang phải");

                if (!visited.contains(x)) {
                    tapBienO.add(trangThaiPhatSinh);
                    visited.add(x);
                }
            }

            if (i % 3 > 0) {
                List<Integer> x = sangTrai(t, i);

                State trangThaiPhatSinh = new State(x, trangThaiXemXet.getC() + 1,
                        0, trangThaiXemXet, "Sang trái");

                if (!visited.contains(x)) {
                    tapBienO.add(trangThaiPhatSinh);
                    visited.add(x);
                }
            }

            if (i - 3 >= 0) {
                List<Integer> x = lenTren(t, i);

                State trangThaiPhatSinh = new State(x, trangThaiXemXet.getC() + 1,
                        0, trangThaiXemXet, "Lên trên");

                if (!visited.contains(x)) {
                    tapBienO.add(trangThaiPhatSinh);
                    visited.add(x);
                }

            }

            if (i + 3 < 9) {
                List<Integer> x = xuongDuoi(t, i);

                State trangThaiPhatSinh = new State(x, trangThaiXemXet.getC() + 1,
                        0, trangThaiXemXet, "Xuống dưới");

                if (!visited.contains(x)) {
                    tapBienO.add(trangThaiPhatSinh);
                    visited.add(x);
                }
            }
        }
        System.out.println("So lan duyet cua BFS: " + soLanDuyet);
        if (st.size() > 0) {
            System.out.println("Chi phi cua BFS: " + (st.size() - 1));
            soBuoc = st.size() - 1;
        }

    }

    public Stack<State> getSt() {
        return st;
    }

    public void setSt() {
        this.st.clear();
    }

    public List<Integer> sangPhai(List<Integer> ans, int i) {
        List<Integer> t = new ArrayList<>();
        t.addAll(ans);
        int temp = t.get(i);
        t.set(i, t.get(i + 1));
        t.set(i + 1, temp);

        return t;

    }

    public List<Integer> sangTrai(List<Integer> ans, int i) {
        List<Integer> t = new ArrayList<>();
        t.addAll(ans);
        int temp = t.get(i);
        t.set(i, t.get(i - 1));
        t.set(i - 1, temp);
        return t;

    }

    public List<Integer> lenTren(List<Integer> ans, int i) {
        List<Integer> t = new ArrayList<>();
        t.addAll(ans);
        int temp = t.get(i);
        t.set(i, t.get(i - 3));
        t.set(i - 3, temp);
        return t;

    }

    public List<Integer> xuongDuoi(List<Integer> ans, int i) {
        List<Integer> t = new ArrayList<>();
        t.addAll(ans);
        int temp = t.get(i);
        t.set(i, t.get(i + 3));
        t.set(i + 3, temp);
        return t;

    }

    public boolean ketThuc(List<Integer> list) {
        int k = 1;
        for (Integer i : list) {
            if (i != k) {
                return false;
            } else {
                k++;
            }
        }
        return true;
    }

    public void in(List<Integer> t) {
        System.out.println(t.get(0) + " " + t.get(1) + " " + t.get(2));
        System.out.println(t.get(3) + " " + t.get(4) + " " + t.get(5));
        System.out.println(t.get(6) + " " + t.get(7) + " " + t.get(8));
        System.out.println("--->");

    }

    public Integer getSoBuoc() {
        return soBuoc;
    }

    public void setSoBuoc(Integer soBuoc) {
        this.soBuoc = soBuoc;
    }

    public Integer getSoLanDuyet() {
        return soLanDuyet;
    }

    public void setSoLanDuyet(Integer soLanDuyet) {
        this.soLanDuyet = soLanDuyet;
    }

}
