package com.xushifei.common.util;

import com.xushifei.common.constants.SysConstants;
import com.xushifei.common.vo.TreeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 树工具类
 *
 * @author xushifei
 * @since 2021/8/23
 */
public class TreeUtils<T> {
  /**
   * 列表转树
   *
   * @param list
   * @param <T>
   * @return
   */
  public static <T extends TreeVO> List<T> listToTree(List<T> list) {
    return listToTree(list, vo -> Objects.equals(SysConstants.ROOT_PARENT_ID, vo.getParentId()));
  }
  /**
   * 列表转树
   *
   * @param list
   * @param rootCondition 根节点表达式
   * @param <T>
   * @return
   */
  public static <T extends TreeVO> List<T> listToTree(List<T> list, Predicate<T> rootCondition) {
    if (CollectionUtils.isEmpty(list)) {
      return Collections.emptyList();
    }
    // key is parentId,value is children
    Map<Long, List<T>> map = list.stream().collect(Collectors.groupingBy(T::getParentId));
    list.forEach(
        node -> {
          // 一般都会传排序条件
          List<T> currentChildren = map.get(node.getId());
          if (!CollectionUtils.isEmpty(currentChildren)) {
            node.setChildren(
                currentChildren.stream()
                    .sorted(Comparator.comparing(T::getSort))
                    .collect(Collectors.toList()));
          } else {
            node.setChildren(Collections.emptyList());
          }
        });
    return list.stream().filter(rootCondition).collect(Collectors.toList());
  }

  /**
   * 根据条件过滤列表后转树
   *
   * @param list
   * @param filterCondition
   * @param rootCondition 是否为根节点表达式
   * @param comparator
   * @param <T>
   * @return
   */
  public static <T extends TreeVO> List<T> filterToTree(
      List<T> list,
      Predicate<T> filterCondition,
      Predicate<T> rootCondition,
      Comparator<T> comparator) {

    if (CollectionUtils.isEmpty(list)) {
      return Collections.emptyList();
    }
    // key is parentId,value is children
    Map<Long, List<T>> map = list.stream().collect(Collectors.groupingBy(T::getParentId));
    // 根节点
    List<T> roots = list.stream().filter(rootCondition).collect(Collectors.toList());
    for (T root : roots) {
      Queue<T> queue = new ArrayDeque<>();
      queue.add(root);
      // 当前节点的子节点列表
      while (!CollectionUtils.isEmpty(queue)) {
        T currentNode = queue.poll();
        // 当前节点过滤后的子节点
        List<T> currentChildren = filterNode(currentNode, filterCondition, map);
        currentNode.setChildren(new ArrayList<>(currentChildren));
        // 对当前子节点排序
        queue.addAll(currentChildren.stream().sorted(comparator).collect(Collectors.toList()));
      }
    }
    return roots.stream().sorted(comparator).collect(Collectors.toList());
  }

  /**
   * 递归找出符合条件的节点
   *
   * @param root
   * @param filterCondition
   * @param map
   * @param <T>
   * @return
   */
  public static <T extends TreeVO> List<T> filterNode(
      T root, Predicate<T> filterCondition, Map<Long, List<T>> map) {
    if (Objects.isNull(root)) {
      return Collections.emptyList();
    }
    List<T> children = map.get(root.getId());
    if (CollectionUtils.isEmpty(children)) {
      return Collections.emptyList();
    }
    List<T> result = new ArrayList<>();
    children.forEach(
        child -> {
          if (filterCondition.test(child)) {
            result.add(child);
          } else {
            // 不符合筛选条件，递归过滤到符合条件的节点
            result.addAll(filterNode(child, filterCondition, map));
          }
        });
    return result;
  }
}
