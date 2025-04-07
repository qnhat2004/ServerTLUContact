import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './unit.reducer';

export const UnitDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const unitEntity = useAppSelector(state => state.unit.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="unitDetailsHeading">
          <Translate contentKey="serverTluContactApp.unit.detail.title">Unit</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{unitEntity.id}</dd>
          <dt>
            <span id="unitCode">
              <Translate contentKey="serverTluContactApp.unit.unitCode">Unit Code</Translate>
            </span>
          </dt>
          <dd>{unitEntity.unitCode}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="serverTluContactApp.unit.name">Name</Translate>
            </span>
          </dt>
          <dd>{unitEntity.name}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="serverTluContactApp.unit.address">Address</Translate>
            </span>
          </dt>
          <dd>{unitEntity.address}</dd>
          <dt>
            <span id="logoUrl">
              <Translate contentKey="serverTluContactApp.unit.logoUrl">Logo Url</Translate>
            </span>
          </dt>
          <dd>{unitEntity.logoUrl}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="serverTluContactApp.unit.email">Email</Translate>
            </span>
          </dt>
          <dd>{unitEntity.email}</dd>
          <dt>
            <span id="fax">
              <Translate contentKey="serverTluContactApp.unit.fax">Fax</Translate>
            </span>
          </dt>
          <dd>{unitEntity.fax}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="serverTluContactApp.unit.type">Type</Translate>
            </span>
          </dt>
          <dd>{unitEntity.type}</dd>
          <dt>
            <Translate contentKey="serverTluContactApp.unit.parentUnit">Parent Unit</Translate>
          </dt>
          <dd>{unitEntity.parentUnit ? unitEntity.parentUnit.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/unit" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/unit/${unitEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default UnitDetail;
