package dz.djezzy.site.acceptance.business.data.specification;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import dz.djezzy.site.acceptance.tools.LocalDateUtil;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GenericRsqlSpecification<T> implements Specification<T> {

    private String property;
    private ComparisonOperator operator;
    private List<String> arguments;

    public GenericRsqlSpecification(final String property, final ComparisonOperator operator, final List<String> arguments) {
        super();
        this.property = property;
        this.operator = operator;
        this.arguments = arguments;
    }

    @Override
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        final List<Object> args = castArguments(root);
        final Object argument = args.get(0);
        Join join = null;
        int lengthJoin = 2;
        if (property.contains(".")) {
            lengthJoin = property.split(Pattern.quote(".")).length;
            for (int i = 0; i < lengthJoin - 1; i++) {
                join = root.join(property.split(Pattern.quote("."))[i]);
            }
        }

        switch (RsqlSearchOperation.getSimpleOperator(operator)) {

            case EQUAL: {
                if (null != join) {
                    if (argument instanceof String) {
                        return builder.like(builder.upper(join.<String>get(property.split(Pattern.quote("."))[lengthJoin - 1])), argument.toString().toUpperCase().replace('*', '%'));
                    } else {
                        return builder.equal(join.<Integer>get(property.split(Pattern.quote("."))[lengthJoin - 1]), argument);
                    }
                } else if (argument instanceof String) {
                    return builder.like(builder.upper(root.get(property)), argument.toString().toUpperCase().replace('*', '%'));
                } else if (argument instanceof LocalDate) {
                    return builder.between(root.get(property), LocalDateUtil.asDate(((LocalDate) argument).atTime(00, 00)), LocalDateUtil.asDate(((LocalDate) argument).atTime(23, 59)));
                } else if (argument == null) {
                    return builder.isNull(root.get(property));
                } else {
                    return builder.equal(root.get(property), argument);
                }
            }
            case NOT_EQUAL: {
                if (argument instanceof String) {
                    return builder.notLike(root.<String>get(property), argument.toString().replace('*', '%'));
                } else if (argument == null) {
                    return builder.isNotNull(root.get(property));
                } else {
                    return builder.notEqual(root.get(property), argument);
                }
            }
            case GREATER_THAN: {
                return builder.greaterThan(root.<String>get(property), argument.toString());
            }
            case GREATER_THAN_OR_EQUAL: {
                return builder.greaterThanOrEqualTo(root.<String>get(property), argument.toString());
            }
            case LESS_THAN: {
                return builder.lessThan(root.<String>get(property), argument.toString());
            }
            case LESS_THAN_OR_EQUAL: {
                return builder.lessThanOrEqualTo(root.<String>get(property), argument.toString());
            }
            case IN:
                return root.get(property).in(args);
            case NOT_IN:
                return builder.not(root.get(property).in(args));
        }

        return null;
    }

    private List<Object> castArguments(final Root<T> root) {
        final Class<? extends Object> type;
        String child;
        if (property.contains(".")) {
            type = root.get(property.split(Pattern.quote("."))[0]).getJavaType();
            child = property.split(Pattern.quote("."))[1];
        } else {
            type = root.get(property).getJavaType();
            child = null;
        }
        final List<Object> args = arguments.stream().map(arg -> {
            if (type.equals(Integer.class)) {
                return Integer.parseInt(arg);
            } else if (type.equals(Long.class)) {
                return Long.parseLong(arg);
            } else if (type.equals(Object.class)) {
                return Long.parseLong(arg);
            } else if (type.equals(Date.class)) {
                return LocalDate.parse(arg.toString());
            } else {
                if (child != null) {
                    if (child.equals("id")) {
                        return Integer.parseInt(arg);
                    } else {
                        return arg;
                    }
                }
                return arg;
            }
        }).collect(Collectors.toList());

        return args;
    }

    private DateFormat format() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }

}

