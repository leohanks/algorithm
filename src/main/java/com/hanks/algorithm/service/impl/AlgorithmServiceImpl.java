package com.hanks.algorithm.service.impl;

import com.hanks.algorithm.common.exception.ServiceException;
import com.hanks.algorithm.service.AlgorithmService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {

    @Override
    public Integer[] bubbleSort(Integer[] metaData) throws ServiceException {
        if (metaData.length <= 0) {
            return new Integer[0];
        }

        Integer temp = 0;
        int lastExchangeIndex = 0;
        int sortBorder = metaData.length - 1;

        for (int i = 0; i < metaData.length; i++) {

            //数组已经有序标记
            boolean isSorted = true;

            for (int j = 0; j < sortBorder; j++) {
                if (metaData[j] > metaData[j+1]) {
                    temp = metaData[j];
                    metaData[j] = metaData[j+1];
                    metaData[j+1] = temp;

                    //交换过元素位置，数组仍未有序
                    isSorted = false;

                    //j是最后一次元素交换的位置
                    lastExchangeIndex = j;
                }
            }

            sortBorder = lastExchangeIndex;

            if (isSorted) {
                break;
            }
        }
        return metaData;
    }

    @Override
    public Integer[] selectionSort(Integer[] metaData) throws ServiceException {
        if (Objects.isNull(metaData) || metaData.length < 1) {
            return new Integer[0];
        }

        for (int i = 0; i < metaData.length - 1; i++) {
            int minValueIndex = i;

            //找出从i开始，最小的元素和其下标
            for (int j = i + 1; j < metaData.length; j++) {
                minValueIndex = metaData[minValueIndex] < metaData[j] ? minValueIndex : j;
            }

            //将无序区最小元素与i元素互换
            Integer temp = metaData[i];
            metaData[i] = metaData[minValueIndex];
            metaData[minValueIndex] = temp;
        }

        return metaData;
    }

    @Override
    public Integer[] quickSort(Integer[] metaData) throws ServiceException {
        if (Objects.isNull(metaData) || metaData.length < 1) {
            return new Integer[0];
        }

        quickSortImpl(metaData, 0, metaData.length-1);

        return metaData;
    }

    private void quickSortImpl(Integer[] metaData, int startIndex, int endIndex) {
        //若开始下标和结束下标重合，则终止递归调用
        if (startIndex >= endIndex) {
            return;
        }

        //获得基准下标
        int pivotIndex = partition(metaData, startIndex, endIndex);

        //递归调用，进行分治
        quickSortImpl(metaData, startIndex, pivotIndex - 1);
        quickSortImpl(metaData, pivotIndex + 1, endIndex);
    }

    private int partition(Integer[] metaData, int startIndex, int endIndex) {
        //选取startIndex为基准元素
        int pivot = metaData[startIndex];
        //初始化左右指针为startIndex和endIndex
        int left = startIndex;
        int right = endIndex;

        //左右指针重合时，则本次指针替换结束
        while (left != right) {
            //右指针元素比基准元素大，则指针左移
            while (left < right && metaData[right] > pivot) {
                right--;
            }
            //左指针元素比基准元素小，则右移
            while (left < right && metaData[left] <= pivot) {
                left++;
            }
            //若在左边找到了比基准元素大的，右边找到了比基准元素小的，则交换左右指针元素
            if (left < right) {
                int temp = metaData[left];
                metaData[left] = metaData[right];
                metaData[right] = temp;
            }
        }

        //左右指针重合，交换基准元素与重合位置元素
        int p = metaData[startIndex];
        metaData[startIndex] = metaData[left];
        metaData[left] = p;

        return left;
    }

    @Override
    public Integer[] mergeSort(Integer[] metaData) throws ServiceException {
        if (Objects.isNull(metaData) || metaData.length < 1) {
            return new Integer[0];
        }

        mergeSortImpl(metaData, 0, metaData.length -1);

        return metaData;
    }

    private void mergeSortImpl(Integer[] metaData, int start, int end) {
        if (start < end) {
            //找到中间坐标
            int mid = (start + end)/2;
            //分治
            mergeSortImpl(metaData, start, mid);
            mergeSortImpl(metaData, mid + 1, end);
            //归并
            merge(metaData, start, mid, end);
        }
    }

    private void merge(Integer[] metaData, int start, int mid, int end) {
        //准备临时数组
        Integer[] tempArray = new Integer[end - start + 1];
        //初始化归并时指针位置
        int p1 = start;
        int p2 = mid + 1;
        int p = 0;

        //将两个小数组复制到tempArray，直到任意小数组的指针指向最后一个
        while(p1 <= mid && p2 <= end) {
            if (metaData[p1] <= metaData[p2]) {
                tempArray[p++] = metaData[p1++];
            } else {
                tempArray[p++] = metaData[p2++];
            }
        }

        //将两个小数组剩余的元素依次复制入tempArray
        while (p1 <= mid) {
            tempArray[p++] = metaData[p1++];
        }
        while (p2 <= end) {
            tempArray[p++] = metaData[p2++];
        }

        //将tempArray的元素顺序覆盖到元数组
        for (int i = 0; i < tempArray.length; i++) {
            metaData[i + start] = tempArray[i];
        }
    }

    @Override
    public Integer[] insertSort(Integer[] metaData) throws ServiceException {
        if (Objects.isNull(metaData) || metaData.length < 1) {
            return new Integer[0];
        }

        for (int i = 1; i < metaData.length; i++) {
            //缓存需要插入的元素
            Integer insertValue = metaData[i];
            //j在移位后，还要用到，所以作用域需要上升到第一个for循环
            int j = i - 1;

            for (; j >= 0 && insertValue < metaData[j]; j--) {
                //前边的元素大，则赋值给后边的空位
                metaData[j + 1] = metaData[j];
            }

            //在结束最后一次j循环时，多做了一次j--，所以此处为j++
            metaData[j + 1] = insertValue;
        }

        return metaData;
    }

    @Override
    public Integer[] shellSort(Integer[] metaData) throws ServiceException {
        if (Objects.isNull(metaData) || metaData.length < 1) {
            return new Integer[0];
        }

        //希尔增数
        int shellInc = metaData.length;

        while (shellInc > 1) {
            //希尔增数每次折半
            shellInc = shellInc / 2;

            for (int i = 0; i < shellInc; i++) {
                //插入排序。希尔排序中的插入排序，因为分组，下标“1”，普遍换成了希尔增数shellInc
                for (int j = i + shellInc; j < metaData.length; j = j + shellInc) {
                    int insertValue = metaData[j];
                    int k = j - shellInc;
                    for (; k >= 0 && insertValue < metaData[k]; k = k - shellInc) {
                        metaData[k + shellInc] = metaData[k];
                    }
                    metaData[k + shellInc] = insertValue;
                }
            }
        }

        return metaData;
    }

    @Override
    public double[] bucketSort(double[] metaData) throws ServiceException {
        if (Objects.isNull(metaData) || metaData.length < 1) {
            return new double[0];
        }

        //得到数列的最大值和最小值，并算出差值d
        double max = metaData[0];
        double min = metaData[0];
        for (int i = 1; i < metaData.length; i++) {
            if (metaData[i] > max) {
                max = metaData[i];
            }
            if (metaData[i] < min) {
                min = metaData[i];
            }
        }
        double d = max - min;

        //初始化桶
        int bucketNum = metaData.length;
        ArrayList<LinkedList<Double>> bucketList = new ArrayList<LinkedList<Double>>(bucketNum);
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<Double>());
        }

        //遍历原始数组，将每个元素放入桶中
        for (int i = 0; i < metaData.length; i++) {
            //此处（bucketNum-1）/d是每个桶的取值范围，再乘以（metaData[i]-min），则得到该元素应归属的桶
            int num = (int)((metaData[i] - min) * (bucketNum - 1) / d);
            bucketList.get(num).add(metaData[i]);
        }

        //对每个桶内部进行排序
        for (int i = 0; i < bucketList.size(); i++) {
            //使用JDK底层的归并排序
            Collections.sort(bucketList.get(i));
        }

        //输出全部元素
        double[] sortedArray = new double[metaData.length];
        int index = 0;
        for (LinkedList<Double> list : bucketList) {
            for (double element : list) {
                sortedArray[index] = element;
                index++;
            }
        }

        return sortedArray;
    }

    @Override
    public Integer[] heapSort(Integer[] metaData) throws ServiceException {
        if (Objects.isNull(metaData) || metaData.length < 1) {
            return new Integer[0];
        }

        //把无序数组构建成二叉堆
        for (int i = (metaData.length - 2)/2; i >= 0; i--) {
            downAdjust(metaData, i, metaData.length);
        }

        //循环删除堆顶元素，移到集合尾部，调节对产生新的堆顶
        for (int i = metaData.length - 1; i > 0; i--) {
            //最后一个元素和第一个元素进行交换
            int temp = metaData[i];
            metaData[i] = metaData[0];
            metaData[0] = temp;
            //下沉调整最大堆
            downAdjust(metaData, 0, i);
        }

        return metaData;
    }

    private void downAdjust(Integer[] metaData, int parentIndex, int length) {
        //temp保存父节点值，用于最后的赋值
        int temp = metaData[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length) {
            //如果有右孩子，且右孩子大于左孩子的值，则定位到右孩子
            if (childIndex + 1 < length && metaData[childIndex + 1] > metaData[childIndex]) {
                childIndex++;
            }

            //如果父节点小于任何一个孩子的值，直接跳出
            if (temp >= metaData[childIndex]) {
                break;
            }

            //无序真正交换，单向赋值即可
            metaData[parentIndex] = metaData[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * childIndex + 1;
        }
        metaData[parentIndex] = temp;
    }
}
