import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntities as getUnits } from 'app/entities/unit/unit.reducer';
import { createEntity, getEntity, reset, updateEntity } from './staff.reducer';

export const StaffUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const users = useAppSelector(state => state.userManagement.users);
  const units = useAppSelector(state => state.unit.entities);
  const staffEntity = useAppSelector(state => state.staff.entity);
  const loading = useAppSelector(state => state.staff.loading);
  const updating = useAppSelector(state => state.staff.updating);
  const updateSuccess = useAppSelector(state => state.staff.updateSuccess);

  const handleClose = () => {
    navigate(`/staff${location.search}`);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getUsers({}));
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
      ...staffEntity,
      ...values,
      user: users.find(it => it.id.toString() === values.user?.toString()),
      unit: units.find(it => it.id.toString() === values.unit?.toString()),
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
          ...staffEntity,
          user: staffEntity?.user?.id,
          unit: staffEntity?.unit?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="serverTluContactApp.staff.home.createOrEditLabel" data-cy="StaffCreateUpdateHeading">
            <Translate contentKey="serverTluContactApp.staff.home.createOrEditLabel">Create or edit a Staff</Translate>
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
                  id="staff-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('serverTluContactApp.staff.staffId')}
                id="staff-staffId"
                name="staffId"
                data-cy="staffId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('serverTluContactApp.staff.fullName')}
                id="staff-fullName"
                name="fullName"
                data-cy="fullName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('serverTluContactApp.staff.position')}
                id="staff-position"
                name="position"
                data-cy="position"
                type="text"
              />
              <ValidatedField
                label={translate('serverTluContactApp.staff.phone')}
                id="staff-phone"
                name="phone"
                data-cy="phone"
                type="text"
              />
              <ValidatedField
                label={translate('serverTluContactApp.staff.address')}
                id="staff-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField
                label={translate('serverTluContactApp.staff.education')}
                id="staff-education"
                name="education"
                data-cy="education"
                type="text"
              />
              <ValidatedField
                label={translate('serverTluContactApp.staff.email')}
                id="staff-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('serverTluContactApp.staff.avatarUrl')}
                id="staff-avatarUrl"
                name="avatarUrl"
                data-cy="avatarUrl"
                type="text"
              />
              <ValidatedField id="staff-user" name="user" data-cy="user" label={translate('serverTluContactApp.staff.user')} type="select">
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="staff-unit" name="unit" data-cy="unit" label={translate('serverTluContactApp.staff.unit')} type="select">
                <option value="" key="0" />
                {units
                  ? units.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/staff" replace color="info">
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

export default StaffUpdate;
