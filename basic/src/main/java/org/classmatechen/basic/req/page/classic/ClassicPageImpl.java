package org.classmatechen.basic.req.page.classic;

import java.util.Objects;

import org.classmatechen.basic.impl.CheckAble;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassicPageImpl implements ClassicPage, CheckAble {

    private Long page = 1L;
    private Long pageSize = 10L;

    @Override
    public boolean updatePage(Long totalPage) {
        if (totalPage > page) {
            page++;
            return true;
        }
        return false;
    }

    @Override
    public String check() {
        if (Objects.isNull(page) || Objects.isNull(pageSize)) {
            return "必须指定page和pageSize";
        }
        return null;
    }
}
