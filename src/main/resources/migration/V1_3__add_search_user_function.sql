drop function if exists smart_user_search;

create function smart_user_search(
    gender varchar(100), first_name varchar(100), surname varchar(100), login varchar(100), id bigint, birthDate timestamp, created timestamp, changed timestamp)
    RETURNS setof m_users
AS
$$

select u.* from m_users u
where u.gender = $1 and u.name = $2 and u.login = $4
limit 1;

$$ language SQL;