package ru.innopolis.byme.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.innopolis.byme.entity.Category;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@Getter
@AllArgsConstructor
public class CategoryTree{
    private final int id;
    private final String name;
    private final int level;

    public static List<CategoryTree> categoryListToTree(List<Category> categoryList){
        List<CategoryTree> categoryTree = new LinkedList<>();
        int numElement = 0;
        int level = 0;

        List<Integer> parentId = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            parentId.add(0);
        }

        while (level >= 0){
            if (numElement < categoryList.size()) {
                if (categoryList.get(numElement).getParentId() == parentId.get(level)) {
                    CategoryTree categoryTreeElement =
                            new CategoryTree(categoryList.get(numElement).getId(),
                                    categoryList.get(numElement).getName(),
                                    level);
                    categoryTree.add(categoryTreeElement);

                    categoryList.remove(numElement);

                    for (Category category : categoryList) {
                        if (categoryTreeElement.id == category.getParentId()) {
                            level++;
                            parentId.set(level, categoryTreeElement.id);
                            numElement = 0;
                            break;
                        }
                    }
                }
                else {
                    numElement++;
                }
            }
            else {
                level--;
                numElement = 0;
            }
        }

        return categoryTree;
    }
}