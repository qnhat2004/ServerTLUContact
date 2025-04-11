import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getUnits } from 'app/entities/unit/unit.reducer';
import { UnitType } from 'app/shared/model/enumerations/unit-type.model';
import { createEntity, getEntity, reset, updateEntity } from './unit.reducer';

export const UnitUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const units = useAppSelector(state => state.unit.entities);
  const unitEntity = useAppSelector(state => state.unit.entity);
  const loading = useAppSelector(state => state.unit.loading);
  const updating = useAppSelector(state => state.unit.updating);
  const updateSuccess = useAppSelector(state => state.unit.updateSuccess);
  const unitTypeValues = Object.keys(UnitType);

  const handleClose = () => {
    navigate(`/unit${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getUnits({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }

    const entity = {
      ...unitEntity,
      ...values,
      parentUnit: units.find(it => it.id.toString() === values.parentUnit?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          type: 'FACULTY',
          ...unitEntity,
          parentUnit: unitEntity?.parentUnit?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="serverTluContactApp.unit.home.createOrEditLabel" data-cy="UnitCreateUpdateHeading">
            <Translate contentKey="serverTluContactApp.unit.home.createOrEditLabel">Create or edit a Unit</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="unit-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('serverTluContactApp.unit.unitCode')}
                id="unit-unitCode"
                name="unitCode"
                data-cy="unitCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('serverTluContactApp.unit.name')}
                id="unit-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('serverTluContactApp.unit.address')}
                id="unit-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField
                label={translate('serverTluContactApp.unit.logoUrl')}
                id="unit-logoUrl"
                name="logoUrl"
                data-cy="logoUrl"
                type="text"
              />
              <ValidatedField
                label={translate('serverTluContactApp.unit.email')}
                id="unit-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{}}
              />
              <ValidatedField label={translate('serverTluContactApp.unit.fax')} id="unit-fax" name="fax" data-cy="fax" type="text" />
              <ValidatedField label={translate('serverTluContactApp.unit.type')} id="unit-type" name="type" data-cy="type" type="select">
                {unitTypeValues.map(unitType => (
                  <option value={unitType} key={unitType}>
                    {translate(`serverTluContactApp.UnitType.${unitType}`)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                id="unit-parentUnit"
                name="parentUnit"
                data-cy="parentUnit"
                label={translate('serverTluContactApp.unit.parentUnit')}
                type="select"
              >
                <option value="" key="0" />
                {units
                  ? units.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/unit" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default UnitUpdate;
