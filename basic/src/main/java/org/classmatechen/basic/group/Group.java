package org.classmatechen.basic.group;

import java.util.List;

public interface Group<P> {

    List<GroupFail<P>> execute();
}
