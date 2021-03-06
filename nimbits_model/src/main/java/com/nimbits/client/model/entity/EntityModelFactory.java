/*
 * Copyright (c) 2013 Nimbits Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either expressed or implied.  See the License for the specific language governing permissions and limitations under the License.
 */

package com.nimbits.client.model.entity;

import com.nimbits.client.enums.EntityType;
import com.nimbits.client.enums.ProtectionLevel;
import com.nimbits.client.model.common.impl.CommonFactory;
import com.nimbits.client.model.trigger.TargetEntity;
import com.nimbits.client.model.trigger.TargetEntityImpl;
import com.nimbits.client.model.trigger.TriggerEntity;
import com.nimbits.client.model.trigger.TriggerEntityImpl;
import com.nimbits.client.model.user.User;

/**
 * Created by bsautner
 * User: benjamin
 * Date: 12/31/11
 * Time: 9:51 AM
 */
public class EntityModelFactory {


    private EntityModelFactory() {
    }

    public static Entity createEntity(final User user, final Entity entity)  {
        final Entity r = new EntityModel(entity);
        final boolean isOwner =  (user != null && entity.getOwner().equals(user.getKey()));
        r.setReadOnly(!isOwner);
        return r;

    }

    public static Entity createEntity(final Entity entity)  {

        return new EntityModel(entity);

    }

    public static Entity createEntity(final EntityName name,
                                      final String description,
                                      final EntityType entityType,
                                      final ProtectionLevel protectionLevel,
                                      final String parentUUID,
                                      final String ownerUUID,
                                      final String uuid) {
        return new EntityModel(name, description, entityType, protectionLevel,  parentUUID,
                ownerUUID, uuid);
    }



    public static Entity createEntity(final EntityName name,
                                      final String description,
                                      final EntityType entityType,
                                      final ProtectionLevel protectionLevel,
                                      final String parentUUID,
                                      final String ownerUUID) {
        return new EntityModel(name, description, entityType, protectionLevel,  parentUUID,
                ownerUUID, "");
    }
    public static Entity createEntity(final String name,
                                      final String description,
                                      final EntityType entityType,
                                      final ProtectionLevel protectionLevel,
                                      final String parent,
                                      final String owner)  {
        EntityName entityName = CommonFactory.createName(name, entityType);
        return new EntityModel(entityName, description, entityType, protectionLevel,  parent,
                owner, "");
    }
    public static Entity createEntity(final User user)  {
        final EntityName name = CommonFactory.createName(user.getEmail().getValue(), EntityType.user);

        return createEntity(name, "", EntityType.user, ProtectionLevel.onlyMe,
                user.getKey(), user.getKey(),null);

    }






    public static Entity createEntity(final EntityName name, final EntityType entityType) {
        return new EntityModel(name,
                "",
                entityType,
                ProtectionLevel.everyone,
                null,

                null,
                null);
    }


    public static Entity createEntity(final Entity user, final EntityName name) {
        return user != null ? new EntityModel(name,
                "",
                EntityType.point,
                ProtectionLevel.everyone,
                user.getKey(),
                user.getKey(),
                user.getKey())

                :

                new EntityModel(name,
                "",
                EntityType.point,
                ProtectionLevel.everyone,
                null,
                null,null 

                );
    }

    public static TriggerEntity createTrigger(final String key) {
        return  new TriggerEntityImpl(key);
    }
    public static TargetEntity createTarget(final String key) {
        return   new TargetEntityImpl(key);
    }
}
