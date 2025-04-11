import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { Translate, ValidatedField, ValidatedForm, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntities as getUnits } from 'app/entities/unit/unit.reducer';
import { createEntity, getEntity, reset, updateEntity } from './student.reducer';

export const StudentUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const users = useAppSelector(state => state.userManagement.users);
  const units = useAppSelector(state => state.unit.entities);
  const studentEntity = useAppSelector(state => state.student.entity);
  const loading = useAppSelector(state => state.student.loading);
  const updating = useAppSelector(state => state.student.updating);
  const updateSuccess = useAppSelector(state => state.student.updateSuccess);

  const handleClose = () => {
    navigate(`/student${location.search}`);
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
      ...studentEntity,
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
          ...studentEntity,
          user: studentEntity?.user?.id,
          unit: studentEntity?.unit?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="serverTluContactApp.student.home.createOrEditLabel" data-cy="StudentCreateUpdateHeading">
            <Translate contentKey="serverTluContactApp.student.home.createOrEditLabel">Create or edit a Student</Translate>
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
                  id="student-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('serverTluContactApp.student.studentId')}
                id="student-studentId"
                name="studentId"
                data-cy="studentId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('serverTluContactApp.student.fullName')}
                id="student-fullName"
                name="fullName"
                data-cy="fullName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('serverTluContactApp.student.phone')}
                id="student-phone"
                name="phone"
                data-cy="phone"
                type="text"
              />
              <ValidatedField
                label={translate('serverTluContactApp.student.address')}
                id="student-address"
                name="address"
                data-cy="address"
                type="text"
              />
              <ValidatedField
                label={translate('serverTluContactApp.student.email')}
                id="student-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('serverTluContactApp.student.avatarUrl')}
                id="student-avatarUrl"
                name="avatarUrl"
                data-cy="avatarUrl"
                type="text"
              />
              <ValidatedField
                id="student-user"
                name="user"
                data-cy="user"
                label={translate('serverTluContactApp.student.user')}
                type="select"
              >
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="student-unit"
                name="unit"
                data-cy="unit"
                label={translate('serverTluContactApp.student.unit')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/student" replace color="info">
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

export default StudentUpdate;
