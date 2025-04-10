import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './student.reducer';

export const StudentDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const studentEntity = useAppSelector(state => state.student.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="studentDetailsHeading">
          <Translate contentKey="serverTluContactApp.student.detail.title">Student</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{studentEntity.id}</dd>
          <dt>
            <span id="studentId">
              <Translate contentKey="serverTluContactApp.student.studentId">Student Id</Translate>
            </span>
          </dt>
          <dd>{studentEntity.studentId}</dd>
          <dt>
            <span id="fullName">
              <Translate contentKey="serverTluContactApp.student.fullName">Full Name</Translate>
            </span>
          </dt>
          <dd>{studentEntity.fullName}</dd>
          <dt>
            <span id="phone">
              <Translate contentKey="serverTluContactApp.student.phone">Phone</Translate>
            </span>
          </dt>
          <dd>{studentEntity.phone}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="serverTluContactApp.student.address">Address</Translate>
            </span>
          </dt>
          <dd>{studentEntity.address}</dd>
          <dt>
            <Translate contentKey="serverTluContactApp.student.user">User</Translate>
          </dt>
          <dd>{studentEntity.user ? studentEntity.user.id : ''}</dd>
          <dt>
            <Translate contentKey="serverTluContactApp.student.unit">Unit</Translate>
          </dt>
          <dd>{studentEntity.unit ? studentEntity.unit.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/student" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/student/${studentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default StudentDetail;
