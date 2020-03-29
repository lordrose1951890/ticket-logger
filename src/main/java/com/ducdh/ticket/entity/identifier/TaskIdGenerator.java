package com.ducdh.ticket.entity.identifier;

import com.ducdh.ticket.util.converter.AlphabeticConverter;
import com.ducdh.ticket.util.converter.impl.AlphabeticConverterImpl;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Stream;

import static com.ducdh.ticket.util.DateTimeFormatter.parse;

public class TaskIdGenerator implements IdentifierGenerator {

    private AlphabeticConverter converter = new AlphabeticConverterImpl();

    @Override
    public Serializable generate(SharedSessionContractImplementor session,
                                 Object obj)
            throws HibernateException {

        String query = String.format("Select %s from %s",
                session.getEntityPersister(obj.getClass().getName(), obj)
                        .getIdentifierPropertyName(),
                obj.getClass().getSimpleName());

        Stream<String> ids = session.createQuery(query).stream();

        int max = ids.map(id -> id.replaceAll("\\d", ""))
                .mapToInt(converter::toNumeric)
                .max()
                .orElse(0);

        String nextId = converter.toAlphabetic(max + 1);

        return parse(new Date(), "ddMMYY") + nextId;
    }
}
