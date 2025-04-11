import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './staff.reducer';

export const StaffDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const staffEntity = useAppSelector(state => state.staff.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="staffDetailsHeading">
          <Translate contentKey="serverTluContactApp.staff.detail.title">Staff</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{staffEntity.id}</dd>
          <dt>
            <span id="staffId">
              <Translate contentKey="serverTluContactApp.staff.staffId">Staff Id</Translate>
            </span>
          </dt>
          <dd>{staffEntity.staffId}</dd>
          <dt>
            <span id="fullName">
              <Translate contentKey="serverTluContactApp.staff.fullName">Full Name</Translate>
            </span>
          </dt>
          <dd>{staffEntity.fullName}</dd>
          <dt>
            <span id="position">
              <Translate contentKey="serverTluContactApp.staff.position">Position</Translate>
            </span>
          </dt>
          <dd>{staffEntity.position}</dd>
          <dt>
            <span id="phone">
              <Translate contentKey="serverTluContactApp.staff.phone">Phone</Translate>
            </span>
          </dt>
          <dd>{staffEntity.phone}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="serverTluContactApp.staff.address">Address</Translate>
            </span>
          </dt>
          <dd>{staffEntity.address}</dd>
          <dt>
            <span id="education">
              <Translate contentKey="serverTluContactApp.staff.education">Education</Translate>
            </span>
          </dt>
          <dd>{staffEntity.education}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="serverTluContactApp.staff.email">Email</Translate>
            </span>
          </dt>
          <dd>{staffEntity.email}</dd>
          <dt>
            <Translate contentKey="serverTluContactApp.staff.user">User</Translate>
          </dt>
          <dd>{staffEntity.user ? staffEntity.user.id : ''}</dd>
          <dt>
            <Translate contentKey="serverTluContactApp.staff.unit">Unit</Translate>
          </dt>
          <dd>{staffEntity.unit ? staffEntity.unit.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/staff" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/staff/${staffEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default StaffDetail;
