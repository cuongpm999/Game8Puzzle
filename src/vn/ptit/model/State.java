package vn.ptit.model;

import java.util.List;
import java.util.Objects;

public class State {

    private List<Integer> list;
    private int c;
    private int f;
    private State parent;
    private String direction;

    public State() {

    }

    public State(List<Integer> list, int c, int f, State parent, String direction) {
        super();
        this.list = list;
        this.c = c;
        this.f = f;
        this.parent = parent;
        this.direction = direction;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.list);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        final State other = (State) obj;
        if (Objects.equals(this.list, other.list) && this.f < other.f) {
            return false;
        }
        return true;
    } 
    
}
