package com.hanks.algorithm.controller;

import com.hanks.algorithm.entity.ao.BucketMetaArray;
import com.hanks.algorithm.entity.ao.MetaArray;
import com.hanks.algorithm.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/algorithm")
public class AlgorithmController {

    @Autowired
    private AlgorithmService algorithmService;

    /**
     * 冒泡排序：相邻两元素互换
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param metaArray
     * @return
     */
    @PostMapping("/bubbleSort")
    public String bubbleSort(@Validated @RequestBody MetaArray metaArray) {
        Integer[] result = algorithmService.bubbleSort(metaArray.getMetaData());
        return Arrays.toString(result);
    }

    /**
     * 选择排序
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param metaArray
     * @return
     */
    @PostMapping("/selectionSort")
    public String selectionSort(@Validated @RequestBody MetaArray metaArray) {
        Integer[] result = algorithmService.selectionSort(metaArray.getMetaData());
        return Arrays.toString(result);
    }

    /**
     * 快速排序
     * 时间复杂度：O(nlogn)，最坏O(n^2)
     * 空间复杂度：O(logn)
     * @param metaArray
     * @return
     */
    @PostMapping("/quickSort")
    public String quickSort(@Validated @RequestBody MetaArray metaArray) {
        Integer[] result = algorithmService.quickSort(metaArray.getMetaData());
        return Arrays.toString(result);
    }

    /**
     * 归并排序
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(n)
     * @param metaArray
     * @return
     */
    @PostMapping("/mergeSort")
    public String mergeSort(@Validated @RequestBody MetaArray metaArray) {
        Integer[] result = algorithmService.mergeSort(metaArray.getMetaData());
        return Arrays.toString(result);
    }

    /**
     * 插入排序
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param metaArray
     * @return
     */
    @PostMapping("/insertSort")
    public String insertSort(@Validated @RequestBody MetaArray metaArray) {
        Integer[] result = algorithmService.insertSort(metaArray.getMetaData());
        return Arrays.toString(result);
    }

    /**
     * 希尔排序
     * 时间复杂度:最坏O(n^2)，Hibbard增量时O(n^(3/2))，Sedgewick增量时O(n^(4/3))
     * 空间复杂度：O(1)
     * @param metaArray
     * @return
     */
    @PostMapping("/shellSort")
    public String shellSort(@Validated @RequestBody MetaArray metaArray) {
        Integer[] result = algorithmService.shellSort(metaArray.getMetaData());
        return Arrays.toString(result);
    }

    /**
     * 桶排序，主要应对非整数排序
     * 时间复杂度O(n+m+n(logn-logm))
     * 空间复杂度O(m+n)
     * @param bucketMetaArray
     * @return
     */
    @PostMapping("/bucketSort")
    public String bucketSort(@Validated @RequestBody BucketMetaArray bucketMetaArray) {
        double[] result = algorithmService.bucketSort(bucketMetaArray.getMetaData());
        return Arrays.toString(result);
    }

    /**
     * 堆排序，基于二叉堆数据结构，底层实现是数组
     * 时间复杂度O(nlogn)
     * 空间复杂度O(1)
     * @param metaArray
     * @return
     */
    @PostMapping("/heapSort")
    public String heapSort(@Validated @RequestBody MetaArray metaArray) {
        Integer[] result = algorithmService.heapSort(metaArray.getMetaData());
        return Arrays.toString(result);
    }
}
