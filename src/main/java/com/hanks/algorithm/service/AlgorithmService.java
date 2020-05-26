package com.hanks.algorithm.service;

import com.hanks.algorithm.common.exception.ServiceException;

public interface AlgorithmService {
    Integer[] bubbleSort(Integer[] metaData) throws ServiceException;

    Integer[] selectionSort(Integer[] metaData) throws ServiceException;

    Integer[] quickSort(Integer[] metaData) throws ServiceException;

    Integer[] mergeSort(Integer[] metaData) throws ServiceException;

    Integer[] insertSort(Integer[] metaData) throws ServiceException;

    Integer[] shellSort(Integer[] metaData) throws ServiceException;

    double[] bucketSort(double[] metaData) throws ServiceException;

    Integer[] heapSort(Integer[] metaData) throws ServiceException;
}
