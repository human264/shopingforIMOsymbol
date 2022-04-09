package com.example.service;


import com.example.constant.OrderStatus;
import com.example.dto.ExcelDto;
import com.example.entity.ItemImg;
import com.example.entity.Member;
import com.example.entity.Order;
import com.example.entity.OrderItem;
import com.example.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ExcelService {

    private final MemberRepository memberRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ItemImgRepository itemImgRepository;

    public List<List<ExcelDto>> excel() {
        List<Order> orders = orderRepository.findOrderByOrderStatus(OrderStatus.ORDER);
        List<List<ExcelDto>> orderItemList = new ArrayList<>();

        for (Order order : orders) {
            Long id = order.getId();
            List<ExcelDto> excelDtoList = getOrderItem(id);
            orderItemList.add(excelDtoList);
        }

        return orderItemList;
    }

    public List<ExcelDto> getOrderItem(Long id) {
        List<OrderItem> orderItems =
                orderItemRepository.findOrderItemByOrderId(id);
        List<ExcelDto> excelDtos = new ArrayList<>();

        long index = 0;
        for (OrderItem orderItem : orderItems) {
            ExcelDto excelDto = new ExcelDto();
            excelDto.setId(index);
            excelDto.setShipNo(orderItem.getShipNo());
            excelDto.setPerson(getPersonName(orderItem.getCreatedBy()));
            excelDto.setDepartment(getDepartment(orderItem));
            excelDto.setDescription(orderItem.getItem().getItemNm());
            excelDto.setImgName(orderItem.getItem().getItemNm());
            excelDto.setQty(orderItem.getCount());
            excelDto.setImgUrl(getImageUrl(orderItem.getItem().getId()));
            excelDto.setPhoneNo(getPhoneNo(orderItem.getCreatedBy()));
            excelDtos.add(excelDto);
            index++;
        }

        return excelDtos;
    }

    private String getPhoneNo(String createdBy) {
        Member member = memberRepository.findByEmail(createdBy);
        return member.getPhoneNumber();
    }

    private String getPersonName(String createdBy) {
        Member member = memberRepository.findByEmail(createdBy);
        return member.getName();
    }

    private String getImageUrl(Long id) {
        System.out.println("-------------------------------");
        System.out.println(itemImgRepository.findByItemIdAndRepimgYn(id, "Y"));
        System.out.println("-------------------------------");

        ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn(id, "Y");

        System.out.println("-------------------------------");
        System.out.println(itemImg.getImgUrl());
        System.out.println("-------------------------------");
        return itemImg.getImgUrl();
    }

    private String getDepartment(OrderItem orderItem) {
        return getDepartmentFromMember(orderItem.getCreatedBy()).getDepartment();
    }

    private Member getDepartmentFromMember(String createdBy) {
        return memberRepository.findByEmail(createdBy);
    }


    public void changedOrderStatus() {
        List<Order> orderByOrderStatus = orderRepository.findOrderByOrderStatus(OrderStatus.ORDER);
        for (Order byOrderStatus : orderByOrderStatus) {
            byOrderStatus.setOrderStatus(OrderStatus.PROCESSING);
        }
    }




}
