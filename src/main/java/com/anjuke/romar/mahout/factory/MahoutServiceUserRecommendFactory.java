/**
 * Copyright 2012 Anjuke Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.anjuke.romar.mahout.factory;

import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.CachingUserSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.ClassUtils;

import com.anjuke.romar.core.RomarConfig;
import com.anjuke.romar.mahout.GenericReloadDataModel;
import com.anjuke.romar.mahout.MahoutService;
import com.anjuke.romar.mahout.RecommenderWrapper;

public class MahoutServiceUserRecommendFactory
        extends AbstractMahoutServiceFactory  implements MahoutServiceFactory {
    @Override
    public MahoutService createService() {
        RomarConfig config = RomarConfig.getInstance();
        Recommender recommender;
        DataModel dataModel = wrapDataModel(new GenericReloadDataModel());
        UserSimilarity similarity = ClassUtils.instantiateAs(
                config.getUserSimilarityClass(), UserSimilarity.class,
                new Class<?>[] {DataModel.class}, new Object[] {dataModel});
        if (config.isUseSimilariyCache()){
            similarity = new CachingUserSimilarity(similarity,
                    config.getSimilarityCacheSize());
        }
        UserNeighborhood neighborhood = ClassUtils.instantiateAs(
                config.getUserNeighborhoodClass(), UserNeighborhood.class,
                new Class<?>[] {int.class, UserSimilarity.class,
                        DataModel.class},
                new Object[] {config.getUserNeighborhoodNearestN(),
                        similarity, dataModel});
        recommender = new GenericUserBasedRecommender(dataModel, neighborhood,
                similarity);

        return new RecommenderWrapper(recommender);
    }
}
