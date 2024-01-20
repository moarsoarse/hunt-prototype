package com.hunt.worker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hunt.worker.ticket.model.Ticket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketMapper extends BaseMapper<Ticket> {
}