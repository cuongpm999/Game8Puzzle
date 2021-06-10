package vn.ptit.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import vn.ptit.model.State;

public class AStar {

    private Stack<State> stState;
    private Integer soBuoc;
    private Integer soLanDuyet;

    public AStar() {
        stState = new Stack<>();
        soLanDuyet = 0;
    }

    public int calHeuristic(List<Integer> list, int c) {
        int h = 0;

        if (list.get(0) != 1) {
            h++;
        }
        if (list.get(1) != 2) {
            h++;
        }
        if (list.get(2) != 3) {
            h++;
        }
        if (list.get(3) != 4) {
            h++;
        }
        if (list.get(4) != 5) {
            h++;
        }
        if (list.get(5) != 6) {
            h++;
        }
        if (list.get(6) != 7) {
            h++;
        }
        if (list.get(7) != 8) {
            h++;
        }

        return h + c;
    }

    private boolean endProcess(List<Integer> list) {
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

    private void printState(List<Integer> list) {
        System.out.println(list.get(0) + " " + list.get(1) + " " + list.get(2));
        System.out.println(list.get(3) + " " + list.get(4) + " " + list.get(5));
        System.out.println(list.get(6) + " " + list.get(7) + " " + list.get(8));
        System.out.println("--->");

    }

    private List<Integer> moveRight(List<Integer> ans, int i) {
        List<Integer> t = new ArrayList<>();
        t.addAll(ans);
        int temp = t.get(i);
        t.set(i, t.get(i + 1));
        t.set(i + 1, temp);

        return t;

    }

    private List<Integer> moveLeft(List<Integer> ans, int i) {
        List<Integer> t = new ArrayList<>();
        t.addAll(ans);
        int temp = t.get(i);
        t.set(i, t.get(i - 1));
        t.set(i - 1, temp);
        return t;

    }

    private List<Integer> moveUp(List<Integer> ans, int i) {
        List<Integer> t = new ArrayList<>();
        t.addAll(ans);
        int temp = t.get(i);
        t.set(i, t.get(i - 3));
        t.set(i - 3, temp);
        return t;

    }

    private List<Integer> moveDown(List<Integer> ans, int i) {
        List<Integer> t = new ArrayList<>();
        t.addAll(ans);
        int temp = t.get(i);
        t.set(i, t.get(i + 3));
        t.set(i + 3, temp);
        return t;

    }

    private State checkStateInList(PriorityQueue<State> list, List<Integer> x) {
        State xx = list.stream().filter(state -> x.equals(state.getList())).findAny().orElse(null);
        return xx;
    }

    public void BFS(State stateStart) {
        soLanDuyet = 0;
        stState.clear();

        List<State> trangThaiDuocMoRong = new ArrayList<>();

        PriorityQueue<State> tapBienO = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return o1.getF() - o2.getF();
            }
        });
        tapBienO.add(stateStart);

        Set<List<Integer>> visited = new HashSet<>();
        visited.add(stateStart.getList());

        while (tapBienO.size() > 0) {
            State trangThaiXemXet = tapBienO.remove();
            List<Integer> t = trangThaiXemXet.getList();
            trangThaiDuocMoRong.add(trangThaiXemXet);

            soLanDuyet++;

            if (endProcess(t)) {
                State res = new State();
                res = trangThaiXemXet;
                while (res != null) {
                    stState.push(res);
                    res = res.getParent();
                }
                break;
            }

            int i = t.indexOf(9);

            if (i % 3 < 2) {
                List<Integer> x = moveRight(t, i);

                State trangThaiPhatSinh = new State(x, trangThaiXemXet.getC() + 1,
                        calHeuristic(x, trangThaiXemXet.getC() + 1), trangThaiXemXet, "Sang phải");

                if (!visited.contains(x)) {
                    tapBienO.add(trangThaiPhatSinh);
                    visited.add(x);
                } else {
                    if (!trangThaiDuocMoRong.contains(trangThaiPhatSinh)) {
                        tapBienO.add(trangThaiPhatSinh);
                    } else if (!tapBienO.contains(trangThaiPhatSinh)) {
                        tapBienO.remove(checkStateInList(tapBienO, x));
                        tapBienO.add(trangThaiPhatSinh);
                    }
                }

            }

            if (i % 3 > 0) {
                List<Integer> x = moveLeft(t, i);

                State trangThaiPhatSinh = new State(x, trangThaiXemXet.getC() + 1,
                        calHeuristic(x, trangThaiXemXet.getC() + 1), trangThaiXemXet, "Sang trái");

                if (!visited.contains(x)) {
                    tapBienO.add(trangThaiPhatSinh);
                    visited.add(x);
                } else {
                    if (!trangThaiDuocMoRong.contains(trangThaiPhatSinh)) {
                        tapBienO.add(trangThaiPhatSinh);
                    } else if (!tapBienO.contains(trangThaiPhatSinh)) {
                        tapBienO.remove(checkStateInList(tapBienO, x));
                        tapBienO.add(trangThaiPhatSinh);
                    }
                }

            }

            if (i - 3 >= 0) {
                List<Integer> x = moveUp(t, i);

                State trangThaiPhatSinh = new State(x, trangThaiXemXet.getC() + 1,
                        calHeuristic(x, trangThaiXemXet.getC() + 1), trangThaiXemXet, "Lên trên");

                if (!visited.contains(x)) {
                    tapBienO.add(trangThaiPhatSinh);
                    visited.add(x);
                } else {
                    if (!trangThaiDuocMoRong.contains(trangThaiPhatSinh)) {
                        tapBienO.add(trangThaiPhatSinh);
                    } else if (!tapBienO.contains(trangThaiPhatSinh)) {
                        tapBienO.remove(checkStateInList(tapBienO, x));
                        tapBienO.add(trangThaiPhatSinh);
                    }
                }

            }

            if (i + 3 < 9) {
                List<Integer> x = moveDown(t, i);

                State trangThaiPhatSinh = new State(x, trangThaiXemXet.getC() + 1,
                        calHeuristic(x, trangThaiXemXet.getC() + 1), trangThaiXemXet, "Xuống dưới");

                if (!visited.contains(x)) {
                    tapBienO.add(trangThaiPhatSinh);
                    visited.add(x);
                } else {
                    if (!trangThaiDuocMoRong.contains(trangThaiPhatSinh)) {
                        tapBienO.add(trangThaiPhatSinh);
                    } else if (!tapBienO.contains(trangThaiPhatSinh)) {
                        tapBienO.remove(checkStateInList(tapBienO, x));
                        tapBienO.add(trangThaiPhatSinh);
                    }
                }

            }
        }

        System.out.println("So lan duyet cua A*: " + soLanDuyet);

        if (stState.size() > 0) {
            System.out.println("Chi phi cua A*: " + (stState.size() - 1));
            soBuoc = stState.size() - 1;
        }

    }

    public Stack<State> getStState() {
        return stState;
    }

    public void setStState() {
        this.stState.clear();
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
