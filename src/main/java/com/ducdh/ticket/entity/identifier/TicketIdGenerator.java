package com.ducdh.ticket.entity.identifier;

import com.ducdh.ticket.util.converter.AlphabeticConverter;
import com.ducdh.ticket.util.converter.impl.AlphabeticConverterImpl;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.ducdh.ticket.util.DateTimeFormatter.parse;

public class TicketIdGenerator implements IdentifierGenerator {

    private AlphabeticConverter converter = new AlphabeticConverterImpl();

    @Override
    public Serializable generate(SharedSessionContractImplementor session,
                                 Object obj) throws HibernateException {

        String query = String.format("Select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        List<String> ids = session.createQuery(query).list();
        Set<Date> dates = new HashSet<>();
        int max;

        ids.stream().forEach(id -> {
            String date = id.replaceAll("[^0-9\\&-]", "");
            dates.add(parse(date, "dd-MM-yy"));
        });

        if (!dates. isEmpty()) {
            Date maxDate = Collections.max(dates, Date::compareTo);
            if (parse(parse(new Date(), "dd-MM-yy"), "dd-MM-yy")
                    .compareTo(maxDate) >= 0) {
                max = ids.stream().map(id -> id.replaceAll("[\\d\\&-]", ""))
                        .mapToInt(converter::toNumeric)
                        .max()
                        .orElse(0);
            } else {
                max = 0;
            }
        } else {
            max = 0;
        }
        String nextId = converter.toAlphabetic(max + 1);

        return parse(new Date(), "dd-MM-yy") + nextId;
    }
}
