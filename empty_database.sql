

--misc

delete from calendar_date;

delete from calendar_date_aud;

update currency set holiday_code=null;

delete from calendar;

delete from calendar_aud;

-- market data

delete from market_quote;

delete from product_historical_measure_value;

delete from product_historical_measure;

delete from product_historical_measure_value;

-- position,  products & trades

delete from product_event_detail;

delete from product_event_detail_aud;

delete from product_event;

delete from product_event_aud;

delete from trade_entity;

delete from trade_entity_aud;

delete from trade_attribute;

delete from trade_attribute_aud;

delete from flow;

delete from trade_group_trade;

delete from trade_group_trade_aud;

delete from trade_group;

delete from trade_group_aud;

delete from trade;

delete from trade_aud;

delete from position_historical_measure;

delete from position_historical_measure_aud;

delete from position_history;

delete from position;

delete from product_reference;

delete from product_reference_aud;

delete from market_data_code;

delete from market_data_code_aud;

delete from contract_event;

delete from contract_event_aud;

delete from product_schedule;

delete from product_schedule_aud;

delete from product_classification;

delete from product_classification_aud;

delete from product_rate where product_id in (select product_id from product where product_type not in ('Cash','Currency Pair')) or product_id is null;

delete from product_rate_aud where product_id in (select product_id from product where product_type not in ('Cash','Currency Pair')) or product_id is null;

delete from product_forex where product_id in (select product_id from product where product_type not in ('Cash','Currency Pair')) or product_id is null;

delete from product_forex_aud where product_id in (select product_id from product where product_type not in ('Cash','Currency Pair')) or product_id is null;

delete from product_curve where product_id in (select product_id from product where product_type not in ('Cash','Currency Pair')) or product_id is null;

delete from product_curve_aud where product_id in (select product_id from product where product_type not in ('Cash','Currency Pair')) or product_id is null;

delete from product_credit where product_id in (select product_id from product where product_type not in ('Cash','Currency Pair')) or product_id is null;

delete from product_credit_aud where product_id in (select product_id from product where product_type not in ('Cash','Currency Pair')) or product_id is null;

delete from product_single_traded where product_id in (select product_id from product where product_type not in ('Cash','Currency Pair')) or product_id is null;

delete from product_single_traded_aud where product_id in (select product_id from product where product_type not in ('Cash','Currency Pair')) or product_id is null;

delete from product_equity where product_id in (select product_id from product where product_type not in ('Cash','Currency Pair')) or product_id is null;

delete from product_equity_aud where product_id in (select product_id from product where product_type not in ('Cash','Currency Pair')) or product_id is null;

delete from product_subproduct;

delete from product_subproduct_aud;

delete from product_underlying;

delete from product_underlying_aud;

delete from schedule_line;

delete from schedule_line_aud;

delete from product where product_type not in ('Cash','Currency Pair');

delete from product_aud where product_type not in ('Cash','Currency Pair');

delete from scheduler;

delete from scheduler_aud;

-- legal entities

delete from collateral_agreement;

delete from agreement_aud;

delete from agreement;

delete from bo_account_intermediary;

delete from bo_account_intermediary_aud;

delete from bo_account;

delete from bo_account_aud;

delete from margin_clearer_rule;

delete from legal_entity_attribute;

delete from legal_entity_attribute_aud;

delete from legal_entity_rating;

delete from legal_entity_rating_aud;

delete from legal_entity_role;

delete from legal_entity_role_aud;

delete from credit_entity;

delete from credit_entity_aud;

delete from credit_event;

delete from credit_event_aud;

update legal_entity set parent_legal_entity_id=null;

delete from legal_entity_aud where short_name not in ('Book Taux'); 

delete from legal_entity where short_name not in ('Book Taux'); 


--  reports

delete from custom_column_detail;

delete from custom_column_detail_aud;

delete from custom_column;

delete from custom_column_aud;

delete from filter_group_filter;

delete from filter_group_filter_aud;

delete from filter_group;

delete from filter_group_aud;

delete from template_column_item where template_id in( select template_id from template where template_name!='default');

delete from template where template_name!='default';

delete from snapshotreport;

-- filters

delete from filter_criteria;

delete from filter_criteria_aud;

delete from filter 
where name!='ALL'
and filter_id not in (select filter_id from template where filter_id is not null)
and name not in (select trade_filter_name from pricers_setting where trade_filter_name is not null) 
and name not in (select trade_filter_name from pricing_setting_item where trade_filter_name is not null);
-- I don't join on custom columns and ccp as i delete them

-- misc

delete from pdf_doc_config;

delete from pdf_doc_config_aud;


-- log

delete from batch_log;






